document.addEventListener('DOMContentLoaded', () => {

    // ==========================================
    // 1. FUNCIÓN MÁESTRA DEL BUSCADOR
    // ==========================================
    function configurarBuscador(idInput, idTbodyResultados, idTablaDestino) {
        const inputBusqueda = document.getElementById(idInput);
        const tbodyResultados = document.getElementById(idTbodyResultados);
        let temporizadorBusqueda;

        // Si la página no tiene estos elementos (ej. estás en otra vista), no hace nada
        if (!inputBusqueda || !tbodyResultados) return;

        inputBusqueda.addEventListener('input', () => {
            clearTimeout(temporizadorBusqueda);
            temporizadorBusqueda = setTimeout(() => {
                realizarBusqueda(inputBusqueda, tbodyResultados, idTablaDestino);
            }, 300);
        });
    }

    function realizarBusqueda(input, tbody, idDestino) {
        const query = input.value.trim();

        if (query.length === 0) {
            tbody.innerHTML = `<tr><td colspan="6" class="body-tabla" style="text-align: center;">Escriba un producto para ver sugerencias...</td></tr>`;
            return;
        }

        tbody.innerHTML = `<tr><td colspan="6" class="body-tabla" style="text-align: center;">Buscando...</td></tr>`;

        fetch(`/api/productos/buscar?q=${query}`)
            .then(response => response.json())
            .then(productos => {
                tbody.innerHTML = '';

                if (productos.length === 0) {
                    tbody.innerHTML = `<tr><td colspan="6" class="body-tabla" style="text-align: center;">No se encontraron productos similares.</td></tr>`;
                    return;
                }

                productos.forEach(prod => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                        <td class="prod-codigo">${prod.codigoProducto}</td>
                        <td class="prod-nombre">${prod.nombreProducto}</td>
                        <td>${prod.stockActualProducto}</td>
                        <td>
                            S/ <input type="number" class="form-control prod-precio" value="${prod.precioVentaProducto}" style="width: 80px;" readonly>
                        </td>
                        <td>
                            <input type="number" class="form-control prod-cantidad" value="1" min="1" max="${prod.stockActualProducto}" style="width: 80px;">
                        </td>
                        <td>
                            <button type="button" class="btn btn-secundario btn-agregar-lista" 
                                    data-id="${prod.idProducto}" 
                                    data-target="${idDestino}">
                                + Añadir
                            </button>
                        </td>
                    `;
                    tbody.appendChild(tr);
                });
            })
            .catch(error => {
                console.error("Error en la búsqueda:", error);
                tbody.innerHTML = `<tr><td colspan="6" class="body-tabla" style="color: red; text-align: center;">Error al conectar con la base de datos.</td></tr>`;
            });
    }

    // ==========================================
    // 2. ACTIVAR LOS BUSCADORES
    // ==========================================
    // Activamos para Salida
    configurarBuscador('busqueda-prod-salida', 'tbody-resultados-salida', 'tabla-detalles-salida');
    // Activamos para Ingreso
    configurarBuscador('busqueda-prod-ingreso', 'tbody-resultados-ingreso', 'tabla-detalles-ingreso');


    // ==========================================
    // 3. LÓGICA DE AGREGAR Y QUITAR (Universal)
    // ==========================================
    document.addEventListener('click', function(e) {

        // ACCIÓN: AGREGAR
        if (e.target && e.target.classList.contains('btn-agregar-lista')) {
            const btn = e.target;
            const filaBusqueda = btn.closest('tr');

            const idProducto = btn.getAttribute('data-id');
            const codigo = filaBusqueda.querySelector('.prod-codigo').innerText;
            const nombre = filaBusqueda.querySelector('.prod-nombre').innerText;
            const precio = parseFloat(filaBusqueda.querySelector('.prod-precio').value);
            const cantidad = parseInt(filaBusqueda.querySelector('.prod-cantidad').value);

            if(isNaN(cantidad) || cantidad <= 0) {
                alert("Por favor, ingrese una cantidad válida.");
                return;
            }

            const subtotal = precio * cantidad;
            const tablaDestinoId = btn.getAttribute('data-target'); // Aquí lee si va a salida o ingreso
            const tbodyDestino = document.getElementById(tablaDestinoId);

            const filaVacia = tbodyDestino.querySelector('.fila-vacia');
            if(filaVacia) filaVacia.remove();

            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td class="body-tabla">
                    ${codigo}<input type="hidden" name="idProducto[]" value="${idProducto}">
                </td>
                <td class="body-tabla">${nombre}</td>
                <td class="body-tabla">
                    ${cantidad}<input type="hidden" name="cantidad[]" value="${cantidad}">
                </td>
                <td class="body-tabla">
                    S/ ${precio.toFixed(2)}<input type="hidden" name="precioUnitario[]" value="${precio}">
                </td>
                <td class="body-tabla subtotal-celda" data-valor="${subtotal}">S/ ${subtotal.toFixed(2)}</td>
                <td class="body-tabla">
                    <button type="button" class="btn btn-secundario btn-eliminar-fila" style="color: red; border-color: red;">Quitar</button>
                </td>
            `;

            tbodyDestino.appendChild(tr);
            recalcularTotal(tbodyDestino);
        }

        // ACCIÓN: QUITAR
        if (e.target && e.target.classList.contains('btn-eliminar-fila')) {
            const btn = e.target;
            const filaAEliminar = btn.closest('tr');
            const tbodyContenedor = filaAEliminar.closest('tbody');

            filaAEliminar.remove();

            if(tbodyContenedor.children.length === 0) {
                tbodyContenedor.innerHTML = `
                    <tr class="fila-vacia">
                        <td colspan="6" class="body-tabla" style="text-align: center; padding: 2rem; color: var(--texto-secundario);">
                            No hay productos en la lista. Use el buscador de arriba.
                        </td>
                    </tr>
                `;
            }

            recalcularTotal(tbodyContenedor);
        }
    });

    // ==========================================
    // 4. FUNCIÓN RECALCULAR TOTAL
    // ==========================================
    function recalcularTotal(tbody) {
        let total = 0;
        const celdasSubtotal = tbody.querySelectorAll('.subtotal-celda');

        celdasSubtotal.forEach(celda => {
            total += parseFloat(celda.getAttribute('data-valor'));
        });

        const tabla = tbody.closest('table');
        const spanTotal = tabla.querySelector('.total-movimiento');
        if(spanTotal) {
            spanTotal.innerText = `S/ ${total.toFixed(2)}`;
        }
    }

});
document.addEventListener('DOMContentLoaded', () => {

    const modal = document.getElementById("modal-detalle-movimiento");

    // --- FUNCIÓN PARA CERRAR EL MODAL ---
    const cerrarModal = () => {
        // Dependiendo de tu CSS, usamos display none o las clases
        modal.style.display = 'none';
        modal.classList.remove("modal-activo");
        modal.classList.add("modal-oculto");
    };

    // Asignar los botones de cerrar
    const btnCerrarSuperior = document.getElementById("btn-cerrar-modal-superior");
    const btnCerrarInferior = document.getElementById("btn-cerrar-modal-inferior");

    if(btnCerrarSuperior) btnCerrarSuperior.addEventListener("click", cerrarModal);
    if(btnCerrarInferior) btnCerrarInferior.addEventListener("click", cerrarModal);

    // Cerrar haciendo clic fuera del modal (en el fondo oscuro)
    modal.addEventListener("click", (e) => {
        if (e.target === modal) cerrarModal();
    });

    // --- FUNCIÓN PARA ABRIR EL MODAL Y TRAER DATOS ---
    document.addEventListener('click', async (e) => {

        if (e.target && (e.target.classList.contains('btn-ver-detalle'))) {
            const btn = e.target;

            // 1. Llenamos la cabecera
            const idMovimiento = btn.getAttribute('data-id');
            document.getElementById('detalle-id-mov').innerText = `#${idMovimiento}`;
            document.getElementById('detalle-tipo').innerText = btn.getAttribute('data-tipo');
            document.getElementById('detalle-fecha').innerText = btn.getAttribute('data-fecha');
            document.getElementById('detalle-responsable').innerText = btn.getAttribute('data-responsable');
            document.getElementById('detalle-origen').innerText = btn.getAttribute('data-origen');
            document.getElementById('detalle-motivo').innerText = btn.getAttribute('data-motivo');
            document.getElementById('detalle-obs').innerText = btn.getAttribute('data-observaciones');
            document.getElementById('detalle-total-dinero').innerText = `S/ ${parseFloat(btn.getAttribute('data-total')).toFixed(2)}`;

            // 2. Mostramos el modal
            modal.style.display = 'flex';
            modal.classList.remove("modal-oculto");
            modal.classList.add("modal-activo");

            // 3. Limpiamos la tabla mientras carga
            const tbodyDetalle = document.getElementById('detalle-tabla-cuerpo');
            tbodyDetalle.innerHTML = '<tr><td colspan="5" class="body-tabla">Cargando productos...</td></tr>';

            // 4. Pedimos los productos a Java
            try {
                const respuesta = await fetch(`/gestion/adminMovimientos/obtenerDetalles?id=${idMovimiento}`);

                // VERIFICACIÓN CLAVE: Revisamos si Java nos devolvió un error 404 o 500
                if (!respuesta.ok) {
                    throw new Error(`Error del servidor: ${respuesta.status}`);
                }

                const productos = await respuesta.json();
                tbodyDetalle.innerHTML = '';

                if (productos.length === 0) {
                    tbodyDetalle.innerHTML = '<tr><td colspan="5">No hay productos registrados.</td></tr>';
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
                console.error("Error en Fetch:", error);

                tbodyDetalle.innerHTML = '<tr><td colspan="5" style="text-align:center; color:red;">Error al cargar los productos. Revisa la consola (F12).</td></tr>';
            }
        }
    });
});