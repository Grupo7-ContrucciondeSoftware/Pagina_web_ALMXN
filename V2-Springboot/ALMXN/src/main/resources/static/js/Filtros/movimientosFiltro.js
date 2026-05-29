document.addEventListener('DOMContentLoaded', () => {

    const btnFiltrar = document.getElementById('btnFiltrar');
    const btnLimpiar = document.getElementById('btnLimpiar');

    if (btnFiltrar) {
        btnFiltrar.addEventListener('click', () => {
            const tipo = document.getElementById('tipo-filtro').value;
            const idProveedor = document.getElementById('proveedor-filtro').value;
            const idUsuario = document.getElementById('usuario-filtro').value;
            const fechaMin = document.getElementById('fechaMin-filtro').value;
            const fechaMax = document.getElementById('fechaMax-filtro').value;

            const parametros = new URLSearchParams();

            if (tipo) parametros.append('tipo', tipo);
            if (idProveedor) parametros.append('idProveedor', idProveedor);
            if (idUsuario) parametros.append('idUsuario', idUsuario);
            if (fechaMin) parametros.append('fechaMin', fechaMin);
            if (fechaMax) parametros.append('fechaMax', fechaMax);

            parametros.append('tab', 'pestaña-historial');

            window.location.href = '/gestion/adminMovimientos?' + parametros.toString();
        });
    }

    if (btnLimpiar) {
        btnLimpiar.addEventListener('click', () => {
            window.location.href = '/gestion/adminMovimientos?tab=pestaña-historial';
        });
    }
});