<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Administrar Proveedores - Sistema de Gestión de Almacén">
    <title>ALMXN - Administrar Proveedores</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminProveedores.css">

</head>
<body>

    <!-- ===== HEADER ===== -->
    <%@ include file="/WEB-INF/views/header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <!-- ===== INPUTS PARA CAMBIAR DE PESTAÑAS ===== -->
        <input type="radio" name="tab" id="pestaña-listaProveedores" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarProveedores" style="display:none">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Administrar Proveedores</h1>
                    <p class="subtitulo-seccion">Gestione los proveedores que abastecen el almacén</p>
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
                <label for="pestaña-listaProveedores" class="pestaña">Lista de Proveedores</label>
                <label for="pestaña-agregarProveedores" class="pestaña">Agregar Proveedor</label>
            </div>


            <!-- ============================================
                           LISTA PROVEEDORES
            ============================================ -->


            <div class="pestaña-contenido" id="contenido-listaProveedores">

                <!-- FILTROS -->
                <div class="filtro-container">

                    <!-- Razon Social -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="razon-filtro">Razón Social: </label>
                        <input class="form-control" type="text" id="razon-filtro" placeholder="Ej: Alicorp">
                    </div>

                    <!-- RUC -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="ruc-filtro">RUC: </label>
                        <input class="form-control" type="text" id="ruc-filtro" placeholder="Ej: 20100055237">
                    </div>

                    <div class="filtro-grupo">
                        <label class="form-label" for="telefono-filtro">Teléfono: </label>
                        <input class="form-control" type="number" id="telefono-filtro" placeholder="Ej: 998877665">
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
                            <th class="header-tabla">RUC</th>
                            <th class="header-tabla">Razón Social</th>
                            <th class="header-tabla">Teléfono</th>
                            <th class="header-tabla">Correo</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>

                    <%-- CONTENIDO DE LA TABLA --%>
                    <tbody class="body-tabla">

                        <c:forEach var="proveedor" items="${listaProveedores}">
                            <tr>
                                <td class="body-tabla">${proveedor.idProveedor}</td>
                                <td class="body-tabla">${proveedor.rucProveedor}</td>
                                <td class="body-tabla">${proveedor.razonSocialProveedor}</td>
                                <td class="body-tabla">${proveedor.telefonoProveedor}</td>
                                <td class="body-tabla">${proveedor.correoProveedor}</td>
                                <td class="body-tabla">
                                <c:choose>
                                    <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                        <a href="/gestion/adminProveedores/editar?id=${proveedor.idProveedor}" class="btn btn-secundario btn-editar">Editar</a>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-secundario" onclick="abrirModalPermiso()" style="font-size: 16px;" >Editar</button>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                        <button type="button" class="btn btn-secundario btn-eliminar-modal"
                                                data-id="${proveedor.idProveedor}" data-nombre="${proveedor.razonSocialProveedor}">
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
                           AGREGAR PROVEEDOR
            ============================================ -->


            <div class="pestaña-contenido" id="contenido-agregarProveedores">

                <!-- FORMULARIO -->
                <section class="form-grupo">

                    <form action="/gestion/adminProveedores/guardar" method="POST">
                        <div class="formulario">

                            <!-- RUC -->
                            <div class="form-grupo">
                                <label for="ruc-proveedor" class="form-label">RUC</label>
                                <input
                                    type="number"
                                    id="ruc-proveedor"
                                    name="rucProveedor"
                                    class="form-control"
                                    placeholder="Ej: 20123456789"
                                    maxlength="11"
                                    min="10000000000"
                                    required
                                >
                            </div>

                            <!-- Razon Social -->
                            <div class="form-grupo">
                                <label for="razonsocial-proveedor" class="form-label">Razón Social</label>
                                <input
                                    type="text"
                                    id="razonsocial-proveedor"
                                    name="razonSocialProveedor"
                                    class="form-control"
                                    placeholder="Ej: Alicorp S.A.A."
                                    required
                                >
                            </div>

                            <!-- Telefono -->
                            <div class="form-grupo">
                                <label for="telefono-proveedor" class="form-label">Teléfono</label>
                                <input
                                    type="number"
                                    id="telefono-proveedor"
                                    name="telefonoProveedor"
                                    class="form-control"
                                    min="900000000"
                                    maxlength="9"
                                    placeholder="Ej: 987654321"
                                >
                            </div>

                            <!-- Correo electrónico -->
                            <div class="form-grupo">
                                <label for="email-proveedor" class="form-label">Correo electrónico</label>
                                <input
                                    type="email"
                                    id="email-proveedor"
                                    name="correoProveedor"
                                    class="form-control"
                                    placeholder="Ej: ventas@empresa.com"
                                >
                            </div>
                        </div>

                        <!-- Botones Guardar/Limpiar -->
                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <c:choose>
                                <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                    <button type="submit" class="btn btn-primario">Agregar Proveedor</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-primario" onclick="abrirModalPermiso()" style="font-size: 16px;" >Agregar Proveedor</button>
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
    <script src="/js/Filtros/proveedorFiltro.js" defer></script>
    <%@ include file="/WEB-INF/views/restriccion.jsp" %>

</body>
</html>