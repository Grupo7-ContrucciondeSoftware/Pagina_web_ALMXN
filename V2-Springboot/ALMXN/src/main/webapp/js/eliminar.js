document.addEventListener('DOMContentLoaded', () => {

    const modalEliminar = document.getElementById('modal-confirmar-eliminar');
    const inputIdEliminar = document.getElementById('input-id-eliminar');
    const textoNombreEliminar = document.getElementById('texto-nombre-eliminar');
    const btnCancelarEliminar = document.getElementById('btn-cancelar-eliminar');

    // 1. Abrir Modal al hacer clic en el botón de la tabla
    document.addEventListener('click', (e) => {
        if (e.target && e.target.classList.contains('btn-eliminar-modal')) {
            const btn = e.target;

            // Sacamos los datos del botón
            const idProducto = btn.getAttribute('data-id');
            const nombreProducto = btn.getAttribute('data-nombre');

            // Pegamos los datos en el Modal
            inputIdEliminar.value = idProducto; // Para el Java
            textoNombreEliminar.innerText = nombreProducto; // Para el Usuario

            // Mostramos el modal
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