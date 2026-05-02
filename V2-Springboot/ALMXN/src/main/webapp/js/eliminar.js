document.addEventListener('DOMContentLoaded', () => {

    const modalEliminar = document.getElementById('modal-confirmar-eliminar');
    const inputIdEliminar = document.getElementById('input-id-eliminar');
    const textoNombreEliminar = document.getElementById('texto-nombre-eliminar');
    const btnCancelarEliminar = document.getElementById('btn-cancelar-eliminar');

    document.addEventListener('click', (e) => {
        if (e.target && e.target.classList.contains('btn-eliminar-modal')) {
            const btn = e.target;

            const idProducto = btn.getAttribute('data-id');
            const nombreProducto = btn.getAttribute('data-nombre');

            inputIdEliminar.value = idProducto;
            textoNombreEliminar.innerText = nombreProducto;

            modalEliminar.style.display = 'flex';
        }
    });

    if (btnCancelarEliminar) {
        btnCancelarEliminar.addEventListener('click', () => {
            modalEliminar.style.display = 'none';
            inputIdEliminar.value = '';
        });
    }
});