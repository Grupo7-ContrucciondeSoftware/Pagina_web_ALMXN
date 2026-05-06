document.addEventListener('DOMContentLoaded', () => {

    const modalEliminar = document.getElementById('modal-confirmar-eliminar');
    const inputIdEliminar = document.getElementById('input-id-eliminar');
    const formEliminar = document.getElementById('form-eliminar-dinamico');
    const textoNombreEliminar = document.getElementById('texto-nombre-eliminar');
    const btnCancelarEliminar = document.getElementById('btn-cancelar-eliminar');

    const botonesEliminar = document.querySelectorAll('.btn-eliminar');

    if (modalEliminar && formEliminar) {

        botonesEliminar.forEach(boton => {
            boton.addEventListener('click', () => {
                const id = boton.getAttribute('data-id');
                const nombre = boton.getAttribute('data-nombre');
                const actionUrl = boton.getAttribute('data-action');
                const paramName = boton.getAttribute('data-param');

                textoNombreEliminar.textContent = nombre;
                inputIdEliminar.value = id;
                inputIdEliminar.name = paramName;
                formEliminar.action = actionUrl;

                modalEliminar.classList.add('modal-activo');
            });
        });

        if (btnCancelarEliminar) {
            btnCancelarEliminar.addEventListener('click', () => {
                modalEliminar.classList.remove('modal-activo');
            });
        }
    }
});