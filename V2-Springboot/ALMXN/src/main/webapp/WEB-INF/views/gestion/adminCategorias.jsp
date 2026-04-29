<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ALMXN - Administrar Categorías</title>

    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminCategorias.css">

    <script src="/js/tema.js" defer></script>
</head>
<body>

    <%@ include file="../header.jsp" %>

    <main id="contenido-principal-gestion">

        <input type="radio" name="tab" id="pestaña-listaCategorias" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarCategorias" style="display:none">
        <input type="radio" name="tab" id="pestaña-editarCategorias" style="display:none">

        <div class="centro-pagina-gestion">

            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Administrar Categorías</h1>
                    <p class="subtitulo-seccion">Gestione las clasificaciones de los productos</p>
                </div>

                <div class="formulario-acciones">
                    <a href="/gestion" class="btn btn-secundario">Volver</a>
                </div>
            </section>

            <div class="pestañas">
                <label for="pestaña-listaCategorias" class="pestaña">Lista de Categorías</label>
                <label for="pestaña-agregarCategorias" class="pestaña">Agregar Categoría</label>
                <label for="pestaña-editarCategorias" class="pestaña">Editar Categoría</label>
            </div>

            <div class="pestaña-contenido" id="contenido-listaCategorias">

                <div class="filtro-container">
                    <div class="filtro-grupo">
                        <label class="form-label" for="nombre-filtro">Nombre de Categoría: </label>
                        <input class="form-control" type="text" id="nombre-filtro" placeholder="Ej: Abarrotes">
                    </div>

                    <div class="filtro-acciones">
                        <button class="btn btn-secundario" id="btnFiltrar">Filtrar</button>
                        <button class="btn btn-secundario" id="btnLimpiar">Limpiar</button>
                    </div>
                </div>

                <table class="tabla">
                    <thead class="header-tabla">
                        <tr>
                            <th class="header-tabla">ID</th>
                            <th class="header-tabla">Nombre</th>
                            <th class="header-tabla">Descripción</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>

                    <tbody class="body-tabla">
                        <c:forEach var="categoria" items="${listaCategorias}">
                            <tr>
                                <td class="body-tabla">${categoria.idCategoria}</td>
                                <td class="body-tabla">${categoria.nombreCategoria}</td>
                                <td class="body-tabla">${categoria.descripcionCategoria}</td>
                                <td class="body-tabla">
                                    <a class="btn btn-secundario btn-editar">Editar</a>
                                    <a class="btn btn-secundario btn-eliminar">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <%-- AGREGAR CATEGORIAS --%>

            <div class="pestaña-contenido" id="contenido-agregarCategorias">

                <section class="form-grupo">
                    <form action="/gestion/adminCategorias/guardar" method="POST">
                        <div class="formulario">
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

                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Agregar Categoría</button>
                        </div>
                    </form>
                </section>
            </div>

            <div class="pestaña-contenido" id="contenido-editarCategorias">

                <section class="form-grupo">
                    <label for="buscar-categoria-editar" class="form-label">Buscar Categoría</label>
                    <div class="buscador-boton">
                        <input
                        type="text"
                        id="buscar-categoria-editar"
                        class="form-control"
                        placeholder="Ej: Lácteos"
                        >
                        <button class="btn btn-primario">Buscar</button>
                    </div>
                </section>

                <section class="form-grupo">
                    <label class="form-label">Seleccione la categoría a editar</label>
                    <table class="tabla">
                        <thead class="header-tabla">
                            <tr>
                                <th class="header-tabla">ID</th>
                                <th class="header-tabla">Nombre</th>
                                <th class="header-tabla">Descripción</th>
                                <th class="header-tabla">Acciones</th>
                            </tr>
                        </thead>
                        <tbody class="body-tabla">
                            <tr>
                                <td class="body-tabla">1</td>
                                <td class="body-tabla">Abarrotes</td>
                                <td class="body-tabla">Productos básicos de despensa</td>
                                <td class="body-tabla">
                                    <a class="btn btn-secundario btn-elegir">Elegir</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </section>

                <section class="form-grupo">
                    <form action=" " method="POST">
                        <input type="hidden" name="idCategoria" id="editar-id-categoria">

                        <div class="formulario">
                            <div class="form-grupo">
                                <label for="editar-nombre-categoria" class="form-label">Nombre de la Categoría</label>
                                <input
                                    type="text"
                                    id="editar-nombre-categoria"
                                    name="nombre"
                                    class="form-control"
                                    required
                                >
                            </div>
                        </div>

                        <div class="form-grupo form-textarea">
                            <label for="editar-descripcion-categoria" class="form-label">Descripción <span class="formulario-opcional">(opcional)</span></label>
                            <textarea
                                id="editar-descripcion-categoria"
                                name="descripcion"
                                class="form-control"
                                rows="3"
                            ></textarea>
                        </div>

                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Guardar cambios</button>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    </main>

    <%@ include file="../footer.jsp" %>

</body>
</html>