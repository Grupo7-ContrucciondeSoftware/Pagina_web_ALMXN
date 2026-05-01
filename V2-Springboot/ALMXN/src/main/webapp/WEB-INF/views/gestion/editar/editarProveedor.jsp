<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Editar Proveedor - Sistema de Gestión de Almacén">
    <title>ALMXN - Editar Proveedor</title>

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

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Editar Proveedores</h1>
                    <p class="subtitulo-seccion">Modificando los datos del Proveedor con ID: ${proveedor.idProveedor}</p>
                </div>

                <!-- BOTON CANCELAR / VOLVER -->
                <div class="formulario-acciones">
                    <a href="/gestion/adminProveedores" class="btn btn-secundario">Cancelar y Volver</a>
                </div>
            </section>

            <!-- CONTENIDO DEL FORMULARIO DE EDICIÓN -->
            <div class="pestaña-contenido" style="display: block;">

                <section class="form-grupo">

                    <form action="/gestion/adminProveedores/actualizar" method="POST">

                        <input type="hidden" name="idProveedor" value="${proveedor.idProveedor}">

                        <div class="formulario">

                            <!-- RUC -->
                            <div class="form-grupo">
                                <label for="ruc-proveedor" class="form-label">RUC</label>
                                <input
                                    type="number"
                                    id="ruc-proveedor"
                                    name="rucProveedor"
                                    class="form-control"
                                    value="${proveedor.rucProveedor}"
                                    placeholder="Ej: 20123456789"
                                    maxlength="11"
                                    required>
                            </div>

                            <!-- Razon Social -->
                            <div class="form-grupo">
                                <label for="razonsocial-proveedor" class="form-label">Razon Social</label>
                                <input
                                    type="text"
                                    id="razonsocial-proveedor"
                                    name="razonSocialProveedor"
                                    class="form-control"
                                    value="${proveedor.razonSocialProveedor}"
                                    required>
                            </div>

                            <!-- Telefono -->
                            <div class="form-grupo">
                                <label for="telefono-proveedor" class="form-label">Teléfono</label>
                                <input
                                    type="number"
                                    id="telefono-proveedor"
                                    name="telefonoProveedor"
                                    class="form-control"
                                    value="${proveedor.telefonoProveedor}"
                                    placeholder="Ej: 987654321"
                                    maxlength="9">
                            </div>

                            <!-- Correo Electronico -->
                            <div class="form-grupo">
                                <label for="email-proveedor" class="form-label">Correo Electrónico</label>
                                <input
                                    type="text"
                                    id="email-proveedor"
                                    name="correoProveedor"
                                    class="form-control"
                                    value="${proveedor.correoProveedor}">
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