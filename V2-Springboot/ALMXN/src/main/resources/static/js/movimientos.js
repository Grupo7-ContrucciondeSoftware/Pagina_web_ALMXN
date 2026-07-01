// ============================================================
//  MODAL DE DETALLE de movimiento
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