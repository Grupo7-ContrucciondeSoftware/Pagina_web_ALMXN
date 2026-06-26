// ============================================================
//  MODALES DE REGISTRO (Salida / Ingreso) y DETALLE
// ============================================================
document.addEventListener('DOMContentLoaded', () => {

    function abrirModal(modal) {
        modal.style.display = 'flex';
        modal.classList.remove('modal-oculto-detalle');
        modal.classList.add('modal-activo-detalle');
    }

    function cerrarModal(modal) {
        modal.style.display = 'none';
        modal.classList.remove('modal-activo-detalle');
        modal.classList.add('modal-oculto-detalle');
    }

    function configurarModal(btnAbrirId, modalId, claseCerrar) {
        const btnAbrir = document.getElementById(btnAbrirId);
        const modal = document.getElementById(modalId);
        if (!btnAbrir || !modal) return;

        btnAbrir.addEventListener('click', () => abrirModal(modal));

        modal.querySelectorAll('.' + claseCerrar).forEach(btn => {
            btn.addEventListener('click', () => cerrarModal(modal));
        });

        modal.addEventListener('click', (e) => {
            if (e.target === modal) cerrarModal(modal);
        });
    }

    configurarModal('btn-abrir-salida', 'modal-registrar-salida', 'btn-cerrar-salida');
    configurarModal('btn-abrir-ingreso', 'modal-registrar-ingreso', 'btn-cerrar-ingreso');


    // ============================================================
    //  AÑADIR / QUITAR PRODUCTOS DEL MOVIMIENTO
    // ============================================================
    function configurarAgregarProducto(selectId, cantidadId, tbodyId, tipo) {
        const btnId = tipo === 'salida' ? 'btn-agregar-salida' : 'btn-agregar-ingreso';
        const btn   = document.getElementById(btnId);
        if (!btn) return;

        btn.addEventListener('click', () => {
            const select  = document.getElementById(selectId);
            const opt     = select.options[select.selectedIndex];
            const tbody   = document.getElementById(tbodyId);

            if (!opt || !opt.value) {
                alert("Seleccione un producto.");
                return;
            }

            const cantidad = parseInt(document.getElementById(cantidadId).value);
            if (isNaN(cantidad) || cantidad <= 0) {
                alert("Ingrese una cantidad válida.");
                return;
            }

            // Validar stock solo en salidas
            if (tipo === 'salida') {
                const stock = parseInt(opt.dataset.stock);
                if (cantidad > stock) {
                    alert(`Stock insuficiente. Solo hay ${stock} unidades disponibles.`);
                    return;
                }
            }

            const id       = opt.value;
            const nombre   = opt.dataset.nombre;
            const precio   = parseFloat(opt.dataset.precio);
            const subtotal = precio * cantidad;

            // Quitar fila vacía si existe
            const filaVacia = tbody.querySelector('.fila-vacia');
            if (filaVacia) filaVacia.remove();

            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td class="body-tabla">
                    ${nombre}
                    <input type="hidden" name="idProducto[]" value="${id}">
                </td>
                <td class="body-tabla">
                    ${cantidad}
                    <input type="hidden" name="cantidad[]" value="${cantidad}">
                </td>
                <td class="body-tabla subtotal-celda" data-valor="${subtotal}">
                    S/ ${subtotal.toFixed(2)}
                    <input type="hidden" name="precioUnitario[]" value="${precio}">
                </td>
                <td class="body-tabla">
                    <button type="button" class="btn btn-secundario btn-eliminar-fila"
                            style="color:red; border-color:red;">
                        Quitar
                    </button>
                </td>
            `;
            tbody.appendChild(tr);
            recalcularTotal(tbody);

            // Resetear el select y cantidad
            select.selectedIndex = 0;
            document.getElementById(cantidadId).value = 1;
        });
    }

    configurarAgregarProducto('select-prod-salida',  'cantidad-prod-salida',  'tabla-detalles-salida',  'salida');
    configurarAgregarProducto('select-prod-ingreso', 'cantidad-prod-ingreso', 'tabla-detalles-ingreso', 'ingreso');

    // Eliminar fila de la lista (delegación de eventos)
    document.addEventListener('click', function (e) {
        if (e.target && e.target.classList.contains('btn-eliminar-fila')) {
            const tbody = e.target.closest('tbody');
            e.target.closest('tr').remove();

            if (tbody.children.length === 0) {
                tbody.innerHTML = `
                    <tr class="fila-vacia">
                        <td colspan="4" class="body-tabla producto-lista">
                            No hay productos en la lista.
                        </td>
                    </tr>`;
            }
            recalcularTotal(tbody);
        }
    });

    function recalcularTotal(tbody) {
        let total = 0;
        tbody.querySelectorAll('.subtotal-celda').forEach(c => {
            total += parseFloat(c.dataset.valor);
        });
        const span = tbody.closest('table').querySelector('.total-movimiento');
        if (span) span.innerText = `S/ ${total.toFixed(2)}`;
    }


    // ============================================================
    //  MODAL — Ver detalle de un movimiento del historial
    // ============================================================
    const modalDetalle = document.getElementById("modal-detalle-movimiento");

    const btnCerrarSuperior = document.getElementById("btn-cerrar-modal-superior");
    const btnCerrarInferior = document.getElementById("btn-cerrar-modal-inferior");

    if (btnCerrarSuperior) btnCerrarSuperior.addEventListener("click", () => cerrarModal(modalDetalle));
    if (btnCerrarInferior) btnCerrarInferior.addEventListener("click", () => cerrarModal(modalDetalle));

    modalDetalle.addEventListener("click", (e) => {
        if (e.target === modalDetalle) cerrarModal(modalDetalle);
    });

    document.addEventListener('click', async (e) => {
        if (e.target && e.target.classList.contains('btn-ver-detalle')) {
            const btn = e.target;

            document.getElementById('detalle-id-mov').innerText       = `#${btn.dataset.id}`;
            document.getElementById('detalle-tipo').innerText          = btn.dataset.tipo;
            document.getElementById('detalle-fecha').innerText         = btn.dataset.fecha;
            document.getElementById('detalle-responsable').innerText   = btn.dataset.responsable;
            document.getElementById('detalle-origen').innerText        = btn.dataset.origen;
            document.getElementById('detalle-motivo').innerText        = btn.dataset.motivo;
            document.getElementById('detalle-obs').innerText           = btn.dataset.observaciones;
            document.getElementById('detalle-total-dinero').innerText  = `S/ ${parseFloat(btn.dataset.total).toFixed(2)}`;

            abrirModal(modalDetalle);

            const tbodyDetalle = document.getElementById('detalle-tabla-cuerpo');
            tbodyDetalle.innerHTML = '<tr><td colspan="5" class="body-tabla" style="text-align:center;">Cargando productos...</td></tr>';

            try {
                const respuesta = await fetch(`/gestion/adminMovimientos/obtenerDetalles?id=${btn.dataset.id}`);

                if (!respuesta.ok) throw new Error(`Error del servidor: ${respuesta.status}`);

                const productos = await respuesta.json();
                tbodyDetalle.innerHTML = '';

                if (productos.length === 0) {
                    tbodyDetalle.innerHTML = '<tr><td colspan="5" class="body-tabla">No hay productos registrados.</td></tr>';
                    return;
                }

                productos.forEach(detalle => {
                    const fila = document.createElement('tr');
                    fila.innerHTML = `
                        <td class="body-tabla">${detalle.producto.codigoProducto}</td>
                        <td class="body-tabla">${detalle.producto.nombreProducto}</td>
                        <td class="body-tabla">${detalle.cantidadDetalleMovimiento}</td>
                        <td class="body-tabla">S/ ${detalle.precioUnitarioDetalleMovimiento.toFixed(2)}</td>
                        <td class="body-tabla">S/ ${detalle.subtotalDetalleMovimiento.toFixed(2)}</td>
                    `;
                    tbodyDetalle.appendChild(fila);
                });

            } catch (error) {
                console.error("Error al cargar detalles:", error);
                tbodyDetalle.innerHTML = '<tr><td colspan="5" style="text-align:center;color:red;">Error al cargar los productos. Revisa la consola (F12).</td></tr>';
            }
        }
    });
});