document.addEventListener('DOMContentLoaded', () => {

    const btnFiltrar = document.getElementById('btnFiltrar');
    const btnLimpiar = document.getElementById('btnLimpiar');

    if (btnFiltrar) {
        btnFiltrar.addEventListener('click', (e) => {
            const nombre = document.getElementById('nombre-filtro').value;
            const estado = document.getElementById('estado-filtro').value;

            const parametros = new URLSearchParams();

            if (nombre) parametros.append('nombre', nombre);
            if (estado) parametros.append('estado', estado);

            parametros.append('tab', 'pestaña-listaCategorias');

            window.location.href = '/gestion/adminCategorias?' + parametros.toString();
        });
    }

    if (btnLimpiar) {
        btnLimpiar.addEventListener('click', () => {
            window.location.href = '/gestion/adminCategorias?tab=pestaña-listaCategorias';
        });
    }
});