// ============================================================
//  REGISTRAR INGRESO — Agregar/Quitar productos y calcular total
// ============================================================
document.addEventListener('DOMContentLoaded', () => {

    const btnAgregar = document.getElementById('btn-agregar-ingreso');
    if (!btnAgregar) return;

    btnAgregar.addEventListener('click', () => {
        const select  = document.getElementById('select-prod-ingreso');
        const opt     = select.options[select.selectedIndex];
        const tbody   = document.getElementById('tabla-detalles-ingreso');

        if (!opt || !opt.value) {
            alert("Seleccione un producto.");
            return;
        }

        const cantidad = parseInt(document.getElementById('cantidad-prod-ingreso').value);
        if (isNaN(cantidad) || cantidad <= 0) {
            alert("Ingrese una cantidad válida.");
            return;
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
        document.getElementById('cantidad-prod-ingreso').value = 1;
    });

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
});
