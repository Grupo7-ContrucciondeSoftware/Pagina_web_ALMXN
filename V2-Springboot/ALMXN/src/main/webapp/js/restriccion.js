function abrirModalPermiso() {
    const modal = document.getElementById('modal-sin-permiso');
    if (modal) {
        modal.style.display = 'flex';
        modal.classList.remove('modal-oculto');
        modal.classList.add('modal-activo');
    }
}

function cerrarModalPermiso() {
    const modal = document.getElementById('modal-sin-permiso');
    if (modal) {
        modal.style.display = 'none';
        modal.classList.remove('modal-activo');
        modal.classList.add('modal-oculto');
    }
}