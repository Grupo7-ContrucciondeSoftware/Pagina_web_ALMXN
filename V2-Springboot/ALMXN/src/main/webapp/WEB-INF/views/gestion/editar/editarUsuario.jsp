<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Editar Usuario - Sistema de Gestión de Almacén">
    <title>ALMXN - Editar Usuario</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminUsuarios.css">

</head>
<body>

    <!-- ===== HEADER ===== -->
    <%@ include file="/WEB-INF/views/header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Editar Usuario</h1>
                    <p class="subtitulo-seccion">Modificando los datos de: ${usuario.nombres} ${usuario.apellidos}</p>
                </div>

                <!-- BOTON CANCELAR / VOLVER -->
                <div class="formulario-acciones">
                    <a href="/gestion/adminUsuarios" class="btn btn-secundario">Cancelar y Volver</a>
                </div>
            </section>

            <!-- FORMULARIO -->
            <div class="pestaña-contenido" style="display: block;">

                <section class="form-grupo">

                    <form action="/gestion/adminUsuarios/guardar" method="POST" class="form-validable">

                        <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">

                        <div class="formulario">

                            <!-- Nombres -->
                            <div class="form-grupo">
                                <label for="nombres-usuario" class="form-label">Nombres</label>
                                <input
                                    type="text"
                                    id="nombres-usuario"
                                    name="nombres"
                                    class="form-control"
                                    value="${usuario.nombres}"
                                    required>
                            </div>

                            <!-- Apellidos -->
                            <div class="form-grupo">
                                <label for="apellidos-usuario" class="form-label">Apellidos</label>
                                <input
                                    type="text"
                                    id="apellidos-usuario"
                                    name="apellidos"
                                    class="form-control"
                                    value="${usuario.apellidos}"
                                    required>
                            </div>

                            <!-- Correo Electrónico -->
                            <div class="form-grupo">
                                <label for="email-usuario" class="form-label">Correo Electrónico</label>
                                <input
                                    type="email"
                                    id="email-usuario"
                                    name="correo"
                                    class="form-control"
                                    value="${usuario.correo}"
                                    required>
                            </div>

                            <!-- Rol -->
                            <div class="form-grupo">
                                <label for="rol-usuario" class="form-label">Rol del Sistema</label>
                                <select id="rol-usuario" name="rol" class="form-control" required>
                                    <option value="" disabled>Seleccione un rol</option>
                                    <option value="Admin" <c:if test="${usuario.rol == 'Admin'}">selected</c:if>>Administrador</option>
                                    <option value="Usuario" <c:if test="${usuario.rol == 'Usuario'}">selected</c:if>>Usuario</option>
                                </select>
                            </div>

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

    <!-- ===== FOOTER ===== -->
    <%@ include file="/WEB-INF/views/footer.jsp" %>

    <!-- ===== SCRIPTS ===== -->
    <script src="/js/tema.js" defer></script>

</body>
</html>