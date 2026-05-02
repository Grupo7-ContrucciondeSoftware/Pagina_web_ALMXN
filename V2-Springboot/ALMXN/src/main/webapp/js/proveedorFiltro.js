document.addEventListener('DOMContentLoaded', () => {

    const btnFiltrar = document.getElementById('btnFiltrar');
    const btnLimpiar = document.getElementById('btnLimpiar');

    if (btnFiltrar) {
        btnFiltrar.addEventListener('click', (e) => {
            const razonSocial = document.getElementById('razon-filtro').value;
            const ruc = document.getElementById('ruc-filtro').value;
            const telefono = document.getElementById('telefono-filtro').value;

            const parametros = new URLSearchParams();

            if (razonSocial) parametros.append('razonSocial', razonSocial);
            if (ruc) parametros.append('ruc', ruc);
            if (telefono) parametros.append('telefono', telefono);

            parametros.append('tab', 'pestaña-listaProveedores');

            window.location.href = '/gestion/adminProveedores?' + parametros.toString();
        });
    }

    if (btnLimpiar) {
        btnLimpiar.addEventListener('click', () => {
            window.location.href = '/gestion/adminProveedores?tab=pestaña-listaProveedores';
        });
    }
});