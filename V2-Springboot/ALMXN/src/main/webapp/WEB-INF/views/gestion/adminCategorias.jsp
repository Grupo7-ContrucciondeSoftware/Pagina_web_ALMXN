<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Administrar Categorías - Sistema de Gestión de Almacén">
    <title>ALMXN - Administrar Categorías</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminCategorias.css">

</head>
<body>

    <!-- ===== HEADER ===== -->
    <%@ include file="/WEB-INF/views/header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <!-- ===== INPUTS PARA CAMBIAR DE PESTAÑAS ===== -->
        <input type="radio" name="tab" id="pestaña-listaCategorias" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarCategorias" style="display:none">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Administrar Categorías</h1>
                    <p class="subtitulo-seccion">Gestione las clasificaciones de los productos</p>
                </div>

                <!-- BOTON VOLVER -->
                <div class="formulario-acciones">
                    <a href="/gestion" class="btn btn-secundario">Volver</a>
                </div>
            </section>

            <!-- ============================================
                           PESTAÑAS
            ============================================ -->

            <div class="pestañas">
                <label for="pestaña-listaCategorias" class="pestaña">Lista de Categorías</label>
                <label for="pestaña-agregarCategorias" class="pestaña">Agregar Categoría</label>
            </div>


            <!-- ============================================
                           LISTA CATEGORIAS
            ============================================ -->


            <div class="pestaña-contenido" id="contenido-listaCategorias">

                <!-- FILTROS -->
                <div class="filtro-container">

                    <!-- Nombre Categoría -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="nombre-filtro">Nombre de Categoría: </label>
                        <input class="form-control" type="text" id="nombre-filtro" placeholder="Ej: Abarrotes">
                    </div>

                    <div class="filtro-grupo">
                        <label class="form-label" for="estado-filtro">Estado:</label>
                        <select id="estado-filtro" name="estado" class="form-control">
                            <option value="Todos" >Sin filtro</option>
                            <option value="Activo" selected>Activo</option>
                            <option value="Inactivo" >Inactivo</option>
                        </select>
                    </div>

                    <!-- Botones -->
                    <div class="filtro-acciones">
                        <button type="button" class="btn btn-secundario" id="btnFiltrar">Filtrar</button>
                        <button type="button" class="btn btn-secundario" id="btnLimpiar">Limpiar</button>
                    </div>
                </div>

                <!-- TABLA -->
                <table class="tabla">

                    <!-- HEADER DE LA TABLA -->
                    <thead class="header-tabla">
                        <tr>
                            <th class="header-tabla">ID</th>
                            <th class="header-tabla">Nombre</th>
                            <th class="header-tabla">Descripción</th>
                            <th class="header-tabla">Estado</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>

                    <%-- CONTENIDO DE LA TABLA --%>
                    <tbody class="body-tabla">

                        <c:forEach var="categoria" items="${listaCategorias}">
                            <tr>
                                <td class="body-tabla">${categoria.idCategoria}</td>
                                <td class="body-tabla">${categoria.nombreCategoria}</td>
                                <td class="body-tabla">${categoria.descripcionCategoria}</td>
                                <td class="body-tabla"><span class="estado ${categoria.estadoCategoria.toLowerCase()}">${categoria.estadoCategoria}</span></td>
                                <td class="body-tabla">
                                    <c:choose>
                                        <c:when test="${categoria.estadoCategoria == 'Activo'}">
                                            <a href="/gestion/adminCategorias/editar?id=${categoria.idCategoria}" class="btn btn-secundario btn-editar">Editar</a>
                                            <c:choose>
                                                <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                                    <button type="button" class="btn btn-secundario btn-eliminar"
                                                            data-id="${categoria.idCategoria}"
                                                            data-nombre="${categoria.nombreCategoria}"
                                                            data-action="/gestion/adminCategorias/eliminar"
                                                            data-param="idCategoria">
                                                        Eliminar
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="button" class="btn btn-secundario btn-falso-eliminar" onclick="abrirModalPermiso()" >Eliminar</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>

                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                                    <form action="/gestion/adminCategorias/activar" method="POST" >
                                                        <input type="hidden" name="idCategoria" value="${categoria.idCategoria}">
                                                        <button type="submit" class="btn btn-secundario btn-activar">
                                                            Activar
                                                        </button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="button" class="btn btn-secundario btn-falso-activar" onclick="abrirModalPermiso()">Activar</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- ============================================
                           AGREGAR CATEGORIA
            ============================================ -->

            <div class="pestaña-contenido" id="contenido-agregarCategorias">

                <!-- FORMULARIO -->
                <section class="form-grupo">

                    <form action="/gestion/adminCategorias/guardar" method="POST">
                        <div class="formulario">

                            <!-- Nombre Categoria -->
                            <div class="form-grupo">
                                <label for="nombre-categoria" class="form-label">Nombre de la Categoría</label>
                                <input
                                    type="text"
                                    id="nombre-categoria"
                                    name="nombreCategoria"
                                    class="form-control"
                                    placeholder="Ej: Abarrotes"
                                    required
                                >
                            </div>

                        </div>

                        <!-- Descripción -->
                        <div class="form-grupo form-textarea">
                            <label for="descripcion-categoria" class="form-label">Descripción <span class="formulario-opcional">(opcional)</span></label>
                            <textarea
                                id="descripcion-categoria"
                                name="descripcionCategoria"
                                class="form-control"
                                placeholder="Descripción breve de la categoría..."
                                rows="3"
                            ></textarea>
                        </div>

                        <!-- Botones Guardar/Limpiar -->
                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Agregar Categoría</button>
                        </div>
                    </form>
                </section>
            </div>

            <%-- ============================================
                        MODAL ELIMINAR PRODUCTO
            ============================================ --%>

            <%@ include file="/WEB-INF/views/modalEliminar.jsp" %>

        </div>
    </main>

    <!-- ===== FOOTER ===== -->
    <%@ include file="/WEB-INF/views/footer.jsp" %>

    <!-- ===== RESTRICCION ===== -->
    <%@ include file="/WEB-INF/views/modalRestriccion.jsp" %>

    <!-- ===== SCRIPTS ===== -->
    <script src="/js/tema.js" defer></script>
    <script src="/js/Filtros/categoriasFiltro.js" defer></script>
    <script src="/js/Eliminar/eliminarFuncion.js" defer></script>

</body>
</html>