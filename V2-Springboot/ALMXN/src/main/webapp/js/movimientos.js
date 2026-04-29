document.addEventListener('DOMContentLoaded', () => {

    document.addEventListener('click', function(e) {

        // ==========================================
        // ACCIÓN: AGREGAR PRODUCTO A LA LISTA
        // ==========================================
        if (e.target && e.target.classList.contains('btn-agregar-lista')) {
            const btn = e.target;
            const filaBusqueda = btn.closest('tr');

            // Extraer los datos de la fila de búsqueda
            const idProducto = btn.getAttribute('data-id');
            const codigo = filaBusqueda.querySelector('.prod-codigo').innerText;
            const nombre = filaBusqueda.querySelector('.prod-nombre').innerText;
            const precio = parseFloat(filaBusqueda.querySelector('.prod-precio').value);
            const cantidad = parseInt(filaBusqueda.querySelector('.prod-cantidad').value);

            // Validaciones básicas
            if(isNaN(cantidad) || cantidad <= 0) {
                alert("Por favor, ingrese una cantidad válida.");
                return;
            }

            const subtotal = precio * cantidad;
            const tablaDestinoId = btn.getAttribute('data-target'); // Saber si va a Salida o Ingreso
            const tbodyDestino = document.getElementById(tablaDestinoId);

            // Quitar el mensaje de "No hay productos" si existe
            const filaVacia = tbodyDestino.querySelector('.fila-vacia');
            if(filaVacia) filaVacia.remove();

            // Construir la nueva fila HTML con los datos (¡y los inputs ocultos para Spring Boot!)
            // Usamos arrays en los names (ej: detalles[].idProducto) para que Java los entienda como una Lista
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>
                    ${codigo}
                    <input type="hidden" name="idProducto[]" value="${idProducto}">
                </td>
                <td>${nombre}</td>
                <td>
                    ${cantidad}
                    <input type="hidden" name="cantidad[]" value="${cantidad}">
                </td>
                <td>
                    S/ ${precio.toFixed(2)}
                    <input type="hidden" name="precioUnitario[]" value="${precio}">
                </td>
                <td class="subtotal-celda" data-valor="${subtotal}">S/ ${subtotal.toFixed(2)}</td>
                <td>
                    <button type="button" class="btn btn-secundario btn-eliminar-fila" style="color: red; border-color: red;">Quitar</button>
                </td>
            `;

            // Agregar la fila a la tabla final
            tbodyDestino.appendChild(tr);

            // Recalcular el total general de esa tabla específica
            recalcularTotal(tbodyDestino);
        }

        // ==========================================
        // ACCIÓN: QUITAR PRODUCTO DE LA LISTA
        // ==========================================
        if (e.target && e.target.classList.contains('btn-eliminar-fila')) {
            const btn = e.target;
            const filaAEliminar = btn.closest('tr');
            const tbodyContenedor = filaAEliminar.closest('tbody');

            filaAEliminar.remove(); // Borrar visualmente la fila

            // Si la tabla se quedó vacía, volver a poner el mensaje
            if(tbodyContenedor.children.length === 0) {
                tbodyContenedor.innerHTML = `
                    <tr class="fila-vacia">
                        <td colspan="6" style="text-align: center; padding: 2rem; color: var(--texto-secundario);">
                            No hay productos en la lista. Use el buscador de arriba.
                        </td>
                    </tr>
                `;
            }

            // Recalcular el total después de borrar
            recalcularTotal(tbodyContenedor);
        }
    });

    // Función auxiliar para sumar todos los subtotales
    function recalcularTotal(tbody) {
        let total = 0;
        // Buscar todas las celdas de subtotal dentro de este tbody específico
        const celdasSubtotal = tbody.querySelectorAll('.subtotal-celda');

        celdasSubtotal.forEach(celda => {
            total += parseFloat(celda.getAttribute('data-valor'));
        });

        // Buscar el span del total que está en el TFOOT de la tabla contenedora
        const tabla = tbody.closest('table');
        const spanTotal = tabla.querySelector('.total-movimiento');
        if(spanTotal) {
            spanTotal.innerText = `S/ ${total.toFixed(2)}`;
        }
    }

});