<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ALMXN - Editar Categoría</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminCategoria.css">

    <script src="/js/tema.js" defer></script>
    <script src="/js/validaciones.js" defer></script>
</head>
<body>

    <%@ include file="/WEB-INF/views/header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Editar Categoria</h1>
                    <p class="subtitulo-seccion">Modificando los datos de la categoria: ${categoria.idCategoria}</p>
                </div>

                <!-- BOTON CANCELAR / VOLVER -->
                <div class="formulario-acciones">
                    <a href="/gestion/adminCategorias" class="btn btn-secundario">Cancelar y Volver</a>
                </div>
            </section>

            <!-- CONTENIDO DEL FORMULARIO DE EDICIÓN -->
            <div class="pestaña-contenido" style="display: block;">

                <section class="form-grupo">

                    <form action="/gestion/adminCategorias/actualizar" method="POST">

                        <input type="hidden" name="idCategoria" value="${categoria.idCategoria}">

                        <div class="formulario">

                            <!-- Código -->
                            <div class="form-grupo">
                                <label for="categoria-nombre" class="form-label">Nombre</label>
                                <input
                                    type="text"
                                    id="categoria-nombre"
                                    name="nombreCategoria"
                                    class="form-control"
                                    value="${categoria.nombreCategoria}"
                                    >
                            </div>
                        </div>

                        <!-- Descripción -->
                        <div class="form-grupo form-textarea">
                            <label for="descripcionCategoria" class="form-label">Descripción <span class="formulario-opcional">(opcional)</span></label>
                            <textarea
                                id="descripcionCategoria"
                                name="descripcionCategoria"
                                class="form-control"
                                rows="3">${categoria.descripcionCategoria}</textarea>
                        </div>

                        <!-- Botones -->
                        <div class="formulario-acciones">
                            <button type="submit" class="btn btn-primario">Guardar Cambios</button>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    </main>

    <!-- ===== PIE DE PÁGINA ===== -->
    <%@ include file="/WEB-INF/views/footer.jsp" %>

</body>
</html>