<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Administrar Usuario - Sistema de Gestión de Almacén">
    <title>ALMXN - Administrar Usuarios</title>

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

        <!-- ===== INPUTS PARA CAMBIAR DE PESTAÑAS ===== -->
        <input type="radio" name="tab" id="pestaña-listaUsuario" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarUsuario" style="display:none">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <h1 class="titulo-seccion">Administrar Usuarios</h1>
                <p class="subtitulo-seccion">Gestione los usuarios del sistema</p>

                <!-- BOTON VOLVER -->
                <div class="formulario-acciones">
                    <a href="/gestion" class="btn btn-secundario">Volver</a>
                </div>
            </section>

            <!-- ============================================
                           PESTAÑAS
            ============================================ -->
            <div class="pestañas">
                <label for="pestaña-listaUsuario" class="pestaña">Lista de Usuario</label>
                <label for="pestaña-agregarUsuario" class="pestaña">Agregar Usuario</label>
            </div>



            <!-- ============================================
                           LISTAR USUARIO
            ============================================ -->
            <div class="pestaña-contenido" id="contenido-listaUsuario">

                <!-- FILTROS -->
                <div class="filtro-container">

                    <!-- Nombre -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="nombre-filtro">Nombre: </label>
                        <input class="form-control" type="text" id="nombre-filtro" placeholder="Ingrese nombre">
                    </div>

                    <!-- Categoría -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="rol-filtro">Rol:</label>
                        <select class="form-control" id="rol-filtro">
                            <option value="">Sin Filtro</option>
                            <option value="Admin">Administrador</option>
                            <option value="Usuario">Usuario</option>
                        </select>
                    </div>

                    <!-- Estado -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="estado-filtro">Estado:</label>
                        <select class="form-control" id="estado-filtro">
                            <option value="">Sin Filtro</option>
                            <option value="Activo">Activo</option>
                            <option value="Bloqueado">Bloqueado</option>
                            <option value="Suspendido">Suspendido</option>
                        </select>
                    </div>

                    <!-- Fecha de Registro -->
                    <div class="filtro-grupo">
                        <label class="form-label">Fecha: </label>
                        <div class="filtro-doble">
                            <input class="form-control" type="date" id="fechaMin-filtro">
                            <input class="form-control" type="date" id="fechaMax-filtro">
                        </div>
                    </div>

                    <!-- Botones -->
                    <div class="filtro-acciones">
                        <button class="btn btn-secundario" id="btnFiltrar">Filtrar</button>
                        <button class="btn btn-secundario" id="btnLimpiar">Limpiar</button>
                    </div>

                </div>

                <!-- TABLA -->
                <table class="tabla">

                    <!-- HEADER DE LA TABLA -->
                    <thead class="header-tabla">
                        <tr>
                            <th class="header-tabla">ID</th>
                            <th class="header-tabla">Nombre</th>
                            <th class="header-tabla">Correo</th>
                            <th class="header-tabla">Fecha Creación</th>
                            <th class="header-tabla">Rol</th>
                            <th class="header-tabla">Estado</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>

                    <%-- CONTENIDO DE LA TABLA --%>
                    <tbody class="body-tabla">

                        <c:forEach var="usuario" items="${listaUsuarios}" >
                            <tr>
                                <td class="body-tabla"><c:out value="${usuario.idUsuario}"/></td>
                                <td class="body-tabla"><c:out value="${usuario.nombres} ${usuario.apellidos}"/></td>
                                <td class="body-tabla"><c:out value="${usuario.correo}"/></td>
                                <td class="body-tabla"><c:out value="${usuario.fechaCreacion}"/></td>
                                <td class="body-tabla">
                                    <span class="rol rol-${usuario.rol.toLowerCase()}">
                                        <c:out value="${usuario.rol}"/>
                                    </span>
                                </td>
                                <td class="body-tabla">
                                    <span class="estado ${usuario.estado.toLowerCase()}">
                                        <c:out value="${usuario.estado}"/>
                                    </span>
                                </td>
                                <td class="body-tabla">
                                    <c:choose>
                                        <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                            <a href="/gestion/adminUsuarios/editar?id=${usuario.idUsuario}" class="btn btn-secundario" id="btn-editar">Editar</a>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-secundario" onclick="abrirModalPermiso()" style="font-size: 16px;" >Editar</button>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                            <button type="button" class="btn btn-secundario btn-eliminar-modal"
                                                    data-id="${usuario.idUsuario}" data-nombre="${usuario.nombres} ${usuario.nombres}">
                                                Eliminar
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-secundario" style="font-size: 16px; border-color: #dc3545;"onclick="abrirModalPermiso()" >Eliminar</button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>


            <!-- ============================================
                           AGREGAR USUARIO
            ============================================ -->


            <div class="pestaña-contenido" id="contenido-agregarUsuario">

                <!-- FORMULARIO -->
                <section class="form-grupo">

                    <form id="form-agregar-usuario" action="/gestion/adminUsuarios/guardar" method="POST" novalidate>
                        <div class="formulario">

                            <!-- Nombres -->
                            <div class="form-grupo">
                                <label for="agr-nombres" class="form-label">Nombres</label>
                                <input
                                    type="text"
                                    id="agr-nombres"
                                    name="nombres"
                                    class="form-control"
                                    placeholder="Ej: Juan Eduardo"
                                    required>
                                <span class="campo-error" id="agr-error-nombres"></span>
                            </div>

                            <!-- Apellidos -->
                            <div class="form-grupo">
                                <label for="agr-apellidos"class="form-label">Apellidos</label>
                                <input
                                    type="text"
                                    id="agr-apellidos"
                                    name="apellidos"
                                    class="form-control"
                                    placeholder="Ej: Ramos Pérez"
                                    required>
                                <span class="campo-error" id="agr-error-apellidos"></span>
                            </div>

                            <!-- Correo electrónico -->
                            <div class="form-grupo">
                                <label for="agr-correo" class="form-label">Correo electrónico</label>
                                <input
                                    type="email"
                                    id="agr-correo"
                                    name="correo"
                                    class="form-control"
                                    placeholder="Ej: juan@email.com"
                                    required>
                                <span class="campo-error" id="agr-error-correo"></span>
                            </div>

                            <!-- Rol -->
                            <div class="form-grupo">
                                <label for="agr-rol" class="form-label">Rol</label>
                                <select id="agr-rol" name="rol" class="form-control" required>
                                    <option value="" disabled selected>Seleccione un rol</option>
                                    <option value="Admin">Administrador</option>
                                    <option value="Usuario">Usuario</option>
                                </select>
                                <span class="campo-error" id="agr-error-rol"></span>
                            </div>

                            <!-- Contraseña -->
                            <div class="form-grupo">
                                <label for="agr-contrasena" class="form-label">Contraseña</label>
                                <input
                                    type="password"
                                    id="agr-contrasena"
                                    name="contraseña"
                                    class="form-control"
                                    placeholder="**********"
                                    required>
                                <div class="fortaleza-barra" id="agr-fortaleza-barra"></div>
                                <div class="fortaleza-texto" id="agr-fortaleza-texto"></div>
                                <span class="campo-error" id="agr-error-contrasena"></span>
                            </div>

                            <!-- Confirmar Contraseña -->
                            <div class="form-grupo">
                                <label for="agr-confirmar" class="form-label">Confirmar Contraseña</label>
                                <input
                                    type="password"
                                    id="agr-confirmar"
                                    name="confirmarContraseña"
                                    class="form-control"
                                    placeholder="**********"
                                    required>
                                <span class="campo-error" id="agr-error-confirmar"></span>
                            </div>

                        </div>

                        <!-- Botones Guardar/Limpiar -->
                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <c:choose>
                                <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                    <button type="submit" class="btn btn-primario">Agregar Usuario</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-primario" onclick="abrirModalPermiso()" style="font-size: 16px;" >Agregar Usuario</button>
                                </c:otherwise>
                            </c:choose>
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
    <script src="/js/validacionUsuarios.js" defer></script>
    <script src="/js/Filtros/usuarioFiltro.js" defer></script>
    <%@ include file="/WEB-INF/views/restriccion.jsp" %>

</body>
</html>