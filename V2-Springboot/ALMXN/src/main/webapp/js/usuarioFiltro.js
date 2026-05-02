document.addEventListener('DOMContentLoaded', () => {

    const btnFiltrar = document.getElementById('btnFiltrar');
    const btnLimpiar = document.getElementById('btnLimpiar');

    if (btnFiltrar) {
        btnFiltrar.addEventListener('click', () => {
            const nombres = document.getElementById('nombre-filtro').value;
            const rol = document.getElementById('rol-filtro').value;
            const estado = document.getElementById('estado-filtro').value;
            const fechaMin = document.getElementById('fechaMin-filtro').value;
            const fechaMax = document.getElementById('fechaMax-filtro').value;

            const parametros = new URLSearchParams();

            if (nombres) parametros.append('nombres',nombres);
            if (rol) parametros.append('rol', rol);
            if (estado) parametros.append('estado', estado);
            if (fechaMin) parametros.append('fechaMin', fechaMin);
            if (fechaMax) parametros.append('fechaMax', fechaMax);

            parametros.append('tab', 'pestaña-listaUsuario');

            window.location.href = '/gestion/adminUsuarios?' + parametros.toString();
        });
    }

    if (btnLimpiar) {
        btnLimpiar.addEventListener('click', () => {
            window.location.href = '/gestion/adminUsuarios?tab=pestaña-listaUsuario';
        });
    }
});