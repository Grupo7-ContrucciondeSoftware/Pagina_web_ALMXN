<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Sistema de Gestión de Almacén - Panel principal">
    <title>ALMXN - Gestión</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/gestion.css">

</head>

<body>

    <%@ include file="header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section id="gestion-inicio" class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Gestión de Almacén</h1>
                    <p class="subtitulo-seccion">Selecciona alguna función:</p>
                </div>

                <div class="buscador-de-modulo">
                    <form class="buscador-formulario" onsubmit="event.preventDefault();">
                        <div class="buscador-boton">
                            <input type="text" id="busquedaGlobal" class="form-control" placeholder="Ingrese módulo a buscar" autocomplete="off">
                            <button type="button" class="btn btn-primario btn-buscar">Buscar</button>
                        </div>
                    </form>

                    <div class="buscador-sugerencias" id="caja-sugerencias-modulos">
                        <span>Sugerencias rápidas:</span>
                        <a href="/gestion/adminUsuarios?tab=pestaña-agregarUsuario" class="tag-sugerencia">Agregar Usuario</a>
                        <a href="/gestion/adminMovimientos?tab=pestaña-salida" class="tag-sugerencia">Registrar Salida</a>
                        <a href="/gestion/adminMovimientos?tab=pestaña-historial" class="tag-sugerencia">Historial Movimientos</a>
                    </div>
                </div>

            </section>

            <!-- CUERPO -->
            <section id="seccion-gestion" class="seccion-gestion" aria-label="Gestionar almacén">

                <!-- FUNCIONES -->
                <div class="gestion-contenedor">

                    <a class="opc gestion-administrarProductos" id="gestion-administrarProductos"
                        href="/gestion/adminProductos">
                        <img src="/img/icono-administrarproducto.svg" class="img img-funcion">
                        <h3 class="opc-titulo">Administrar Productos</h3>
                    </a>

                    <a class="opc gestion-adminUsuarios" id="gestion-adminUsuarios"
                        href="/gestion/adminUsuarios">
                        <img src="/img/icono-administrarusuario.svg" class="img img-funcion">
                        <h3 class="opc-titulo">Administrar Usuarios</h3>
                    </a>

                    <a class="opc gestion-adminMovimientos" id="gestion-adminMovimientos"
                        href="/gestion/adminMovimientos">
                        <img src="/img/icono-ingresos.svg" class="img img-funcion">
                        <h3 class="opc-titulo">Administrar Movimientos</h3>
                    </a>

                    <a class="opc gestion-adminCategorias" id="gestion-adminCategorias"
                        href="/gestion/adminCategorias">
                        <img src="/img/icono-administrarcategorias.svg" class="img img-funcion">
                        <h3 class="opc-titulo">Administrar Categorías</h3>
                    </a>

                    <a class="opc gestion-adminProveedores" id="gestion-adminProveedores"
                        href="/gestion/adminProveedores">
                        <img src="/img/icono-administrarproveedores.svg" class="img img-funcion">
                        <h3 class="opc-titulo">Administrar Proveedores</h3>
                    </a>

                </div>

            </section>

        </div>

    </main>

    <!-- ===== FOOTER ===== -->
    <%@ include file="footer.jsp" %>

    <!-- ===== SCRIPTS ===== -->
    <script src="/js/tema.js" defer></script>
    <script src="/js/buscadorModulos.js" defer></script>

</body>

</html>