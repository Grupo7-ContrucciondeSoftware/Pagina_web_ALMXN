document.addEventListener('DOMContentLoaded', function () {
    const modulosDelSistema = [
        // Movimientos
        { nombre: "Registrar Ingreso de Almacén", url: "/gestion/adminMovimientos?tab=pestaña-ingreso" },
        { nombre: "Registrar Salida / Despacho", url: "/gestion/adminMovimientos?tab=pestaña-salida" },
        { nombre: "Historial de Movimientos", url: "/gestion/adminMovimientos?tab=pestaña-historial" },

        // Productos
        { nombre: "Catálogo de Productos", url: "/gestion/adminProductos?tab=pestaña-listaProducto" },
        { nombre: "Agregar Nuevo Producto", url: "/gestion/adminProductos?tab=pestaña-agregarProducto" },

        // Usuarios
        { nombre: "Lista de Usuarios", url: "/gestion/adminUsuarios?tab=pestaña-listaUsuario" },
        { nombre: "Agregar Nuevo Usuario", url: "/gestion/adminUsuarios?tab=pestaña-agregarUsuario" },

        // Proveedores
        { nombre: "Lista de Proveedores", url: "/gestion/adminProveedores?tab=pestaña-listaProveedores" },
        { nombre: "Agregar Nuevo Proveedor", url: "/gestion/adminProveedores?tab=pestaña-agregarProveedores" },

        // Categorias
        { nombre: "Lista de Categorias", url: "/gestion/adminCategorias?tab=pestaña-listaCategorias" },
        { nombre: "Agregar Nueva Categoria", url: "/gestion/adminCategorias?tab=pestaña-agregarCategorias" },

    ];
    const inputBuscador = document.getElementById('busquedaGlobal');
    const cajaSugerencias = document.getElementById('caja-sugerencias-modulos');

    const htmlSugerenciasDefault = cajaSugerencias.innerHTML;

    inputBuscador.addEventListener('input', () => {
        const texto = inputBuscador.value.toLowerCase().trim();

        if (texto.length === 0) {
            cajaSugerencias.innerHTML = htmlSugerenciasDefault;
            return;
        }

        const resultados = modulosDelSistema.filter(mod =>
            mod.nombre.toLowerCase().includes(texto)
        );

        cajaSugerencias.innerHTML = '<span>Resultados:</span>';

        if (resultados.length === 0) {
            cajaSugerencias.innerHTML += '<span style="color: var(--texto-secundario); font-size: 0.9rem; margin-left: 10px;">No se encontraron módulos.</span>';
            return;
        }

        resultados.forEach(mod => {
            const link = document.createElement('a');
            link.href = mod.url;
            link.innerText = mod.nombre;
            link.classList.add('tag-sugerencia');
            cajaSugerencias.appendChild(link);
        });
    });
});