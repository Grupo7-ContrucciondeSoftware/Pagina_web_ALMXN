<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ALMXN - Administrar Usuarios</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminUsuarios.css">

    <script src="/js/tema.js" defer></script>

</head>
<body>

    <%@ include file="/WEB-INF/views/header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <!-- ===== INPUTS PARA CAMBIAR DE PESTAÑAS ===== -->
        <input type="radio" name="tab" id="pestaña-listaUsuario" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarUsuario" style="display:none">
        <input type="radio" name="tab" id="pestaña-editarUsuario" style="display:none">

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
                <label for="pestaña-editarUsuario" class="pestaña">Editar Usuario</label>
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
                            <option value="Administrador">Administrador</option>
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
                                <td class="body-tabla"><c:out value="${usuario.nombres}"/></td>
                                <td class="body-tabla"><c:out value="${usuario.correo}"/></td>
                                <td class="body-tabla"><c:out value="${usuario.fechaCreacion}"/></td>
                                <td class="body-tabla">
                                    <span class="rol ${usuario.rol.toLowerCase()}">
                                        <c:out value="${usuario.rol}"/>
                                    </span>
                                </td>
                                <td class="body-tabla">
                                    <span class="estado ${usuario.estado.toLowerCase()}">
                                        <c:out value="${usuario.estado}"/>
                                    </span>
                                </td>
                                <td class="body-tabla">
                                    <a class="btn btn-secundario" id="btn-editar">Editar</a>
                                    <a class="btn btn-secundario" id="btn-eliminar">Eliminar</a>
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

                    <form action="/gestion/adminUsuarios/guardar" method="POST">
                        <div class="formulario">

                            <!-- Nombres -->
                            <div class="form-grupo">
                                <label for="nombres-usuario" class="form-label">Nombres</label>
                                <input
                                    type="text"
                                    id="nombres-usuario"
                                    name="nombres"
                                    class="form-control"
                                    placeholder="Ej: Juan Eduardo"
                                    required
                                >
                            </div>

                            <!-- Apellidos -->
                            <div class="form-grupo">
                                <label for="apellidos-usuario" class="form-label">Apellidos</label>
                                <input
                                    type="text"
                                    id="apellidos-usuario"
                                    name="apellidos"
                                    class="form-control"
                                    placeholder="Ej: Ramos Pérez"
                                    required
                                >
                            </div>

                            <!-- Correo electrónico -->
                            <div class="form-grupo">
                                <label for="email-usuario" class="form-label">Correo electrónico</label>
                                <input
                                    type="email"
                                    id="email-usuario"
                                    name="correo"
                                    class="form-control"
                                    placeholder="Ej: juan@email.com"
                                    required
                                >
                            </div>

                            <!-- Rol -->
                            <div class="form-grupo">
                                <label for="usuario-rol" class="form-label">Rol</label>
                                <select id="usuario-rol" name="rol" class="form-control" required>
                                    <option value="" disabled selected>Seleccione un rol</option>
                                    <option value="Admin">Administrador</option>
                                    <option value="Usuario">Usuario</option>
                                </select>
                            </div>

                            <!-- Contraeña -->
                            <div class="form-grupo">
                                <label for="contraseña-usuario" class="form-label">Contraseña</label>
                                <input
                                    type="password"
                                    id="contraseña-usuario"
                                    name="contraseña"
                                    class="form-control"
                                    placeholder="**********"
                                    required
                                >
                            </div>

                            <!-- Confirmar contraeña -->
                            <div class="form-grupo">
                                <label for="confirmar-contraseña-usuario" class="form-label">Confirmar Contraseña</label>
                                <input
                                    type="password"
                                    id="confirmar-contraseña-usuario"
                                    name="confirmarContraseña"
                                    class="form-control"
                                    placeholder="**********"
                                    required
                                >
                            </div>
                        </div>

                        <!-- Botones Guardar/Limpiar -->
                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Agregar Usuario</button>
                        </div>

                    </form>

                </section>

            </div>


            <!-- ============================================
                           EDITAR USUARIO
            ============================================ -->


            <div class="pestaña-contenido" id="contenido-editarUsuario">

                <!-- Nombre de Usuario -->
                <section class="form-grupo">

                    <label for="nombre-usuario-editar" class="form-label">Nombre del producto</label>
                    <div class="buscador-boton">
                        <input
                        type="text"
                        id="nombre-usuario-editar"
                        name="nombre-usuario-editar"
                        class="form-control"
                        placeholder="Ej: Juan Ramos"
                        required
                        >
                        <button class="btn btn-primario">Buscar</button>
                    </div>
                </section>

                <!-- TABLA -->
                <section class="form-grupo">
                    <label class="form-label">Seleccione usuario</label>

                    <table class="tabla">

                    <!-- HEADER DE LA TABLA -->
                    <thead class="header-tabla">
                        <tr>
                            <th class="header-tabla">ID</th>
                            <th class="header-tabla">Nombre</th>
                            <th class="header-tabla">Correo</th>
                            <th class="header-tabla">Fecha Registro</th>
                            <th class="header-tabla">Rol</th>
                            <th class="header-tabla">Estado</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>

                    <!-- CONTENIDO DE LA TABLA -->
                    <tbody class="body-tabla">
                        <tr>
                            <td class="body-tabla">1</td>
                            <td class="body-tabla">Juan Ramos</td>
                            <td class="body-tabla">juan@gmail.com</td>
                            <td class="body-tabla">14-04-2026</td>
                            <td class="body-tabla"><span class="rol admin">Admin</span></td>
                            <td class="body-tabla"><span class="estado activo">Activo</span></td>
                            <td class="body-tabla">
                                <a class="btn btn-secundario btn-elegir">Elegir</a>
                            </td>
                        </tr>
                        <tr>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                        </tr>
                        <tr>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                            <td class="body-tabla">-</td>
                        </tr>
                    </tbody>
                </table>
                </section>

                <!-- FORMULARIO -->
                <section class="form-grupo">

                    <div class="formulario">

                        <!-- Nombres -->
                        <div class="form-grupo">
                            <label for="nombres-usuario" class="form-label">Nombres</label>
                            <input
                                type="text"
                                id="nombres-usuario"
                                name="nombres-usuario"
                                class="form-control"
                                placeholder="Ej: Juan Eduardo"
                                required
                            >
                        </div>

                        <!-- Apellidos -->
                        <div class="form-grupo">
                            <label for="apellidos-usuario" class="form-label">Apellidos</label>
                            <input
                                type="text"
                                id="apellidos-usuario"
                                name="apellidos-usuario"
                                class="form-control"
                                placeholder="Ej: Ramos Pérez"
                                required
                            >
                        </div>

                        <!-- Correo electrónico -->
                        <div class="form-grupo">
                            <label for="email-usuario" class="form-label">Correo electrónico</label>
                            <input
                                type="email"
                                id="email-usuario"
                                name="email-usuario"
                                class="form-control"
                                placeholder="Ej: juan@email.com"
                                required
                            >
                        </div>

                        <!-- Rol -->
                        <div class="form-grupo">
                            <label for="usuario-rol" class="form-label">Rol</label>
                            <select id="usuario-rol" name="usuario-rol" class="form-control">
                                <option value="" disabled selected>Seleccione un rol</option>
                                <option value="1">Administrador</option>
                                <option value="2">Usuario</option>
                            </select>
                        </div>

                        <!-- Contraeña -->
                        <div class="form-grupo">
                            <label for="contraseña-usuario" class="form-label">Contraseña</label>
                            <input
                                type="password"
                                id="contraseña-usuario"
                                name="contraseña-usuario"
                                class="form-control"
                                placeholder="**********"
                                required
                            >
                        </div>

                        <!-- Confirmar contraeña -->
                        <div class="form-grupo">
                            <label for="confirmar-contraseña-usuario" class="form-label">Confirmar Contraseña</label>
                            <input
                                type="password"
                                id="confirmar-contraseña-usuario"
                                name="confirmar-contraseña-usuario"
                                class="form-control"
                                placeholder="**********"
                                required
                            >
                        </div>
                    </div>

                    <!-- Botones Guardar/Limpiar -->
                    <div class="formulario-acciones">
                        <button type="reset" class="btn btn-secundario">Limpiar</button>
                        <button type="submit" class="btn btn-primario">Guardar Cambios</button>
                    </div>

                </section>

            </div>

        </div>

    </main>

    <!-- ===== PIE DE PÁGINA ===== -->
    <%@ include file="/WEB-INF/views/footer.jsp" %>

</body>
</html>