document.addEventListener('DOMContentLoaded', () => {

    const btnFiltrar = document.getElementById('btnFiltrar');
    const btnLimpiar = document.getElementById('btnLimpiar');

    if (btnFiltrar) {
        btnFiltrar.addEventListener('click', (e) => {
            e.preventDefault();
            const nombre = document.getElementById('nombre-filtro').value;
            const idCategoria = document.getElementById('categoria-filtro').value;
            const stockMin = document.getElementById('stockmin-filtro').value;
            const stockMax = document.getElementById('stockmax-filtro').value;
            const precioMin = document.getElementById('preciomin-filtro').value;
            const precioMax = document.getElementById('preciomax-filtro').value;
            const fechaMin = document.getElementById('fechaMin-filtro').value;
            const fechaMax = document.getElementById('fechaMax-filtro').value;

            const parametros = new URLSearchParams();

            if (nombre) parametros.append('nombre', nombre);
            if (idCategoria) parametros.append('idCategoria', idCategoria);
            if (stockMin) parametros.append('stockMin', stockMin);
            if (stockMax) parametros.append('stockMax', stockMax);
            if (precioMin) parametros.append('precioMin', precioMin);
            if (precioMax) parametros.append('precioMax', precioMax);
            if (fechaMin) parametros.append('fechaMin', fechaMin);
            if (fechaMax) parametros.append('fechaMax', fechaMax);

            parametros.append('tab', 'pestaña-listaProducto');

            window.location.href = '/gestion/adminProductos?' + parametros.toString();
        });
    }

    if (btnLimpiar) {
        btnLimpiar.addEventListener('click', () => {
            window.location.href = '/gestion/adminProductos?tab=pestaña-listaProducto';
        });
    }
});