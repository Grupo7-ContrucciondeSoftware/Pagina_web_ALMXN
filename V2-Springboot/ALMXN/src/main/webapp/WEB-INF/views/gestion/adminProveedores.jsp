<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ALMXN - Administrar Proveedores</title>

    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminProveedores.css">

    <script src="/js/tema.js" defer></script>
</head>
<body>

    <%@ include file="../header.jsp" %>

    <main id="contenido-principal-gestion">

        <input type="radio" name="tab" id="pestaña-listaProveedores" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarProveedores" style="display:none">
        <input type="radio" name="tab" id="pestaña-editarProveedores" style="display:none">

        <div class="centro-pagina-gestion">

            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Administrar Proveedores</h1>
                    <p class="subtitulo-seccion">Gestione las empresas que abastecen el almacén</p>
                </div>

                <div class="formulario-acciones">
                    <a href="/gestion" class="btn btn-secundario">Volver</a>
                </div>
            </section>

            <div class="pestañas">
                <label for="pestaña-listaProveedores" class="pestaña">Lista de Proveedores</label>
                <label for="pestaña-agregarProveedores" class="pestaña">Agregar Proveedor</label>
                <label for="pestaña-editarProveedores" class="pestaña">Editar Proveedor</label>
            </div>

            <div class="pestaña-contenido" id="contenido-listaProveedores">

                <div class="filtro-container">
                    <div class="filtro-grupo">
                        <label class="form-label" for="razon-filtro">Razón Social: </label>
                        <input class="form-control" type="text" id="razon-filtro" placeholder="Ej: Alicorp">
                    </div>

                    <div class="filtro-grupo">
                        <label class="form-label" for="ruc-filtro">RUC: </label>
                        <input class="form-control" type="text" id="ruc-filtro" placeholder="Ej: 20100055237">
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
                            <th class="header-tabla">RUC</th>
                            <th class="header-tabla">Razón Social</th>
                            <th class="header-tabla">Teléfono</th>
                            <th class="header-tabla">Correo</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>

                    <tbody class="body-tabla">
                        <c:forEach var="proveedor" items="${listaProveedores}">
                            <tr>
                                <td class="body-tabla">${proveedor.idProveedor}</td>
                                <td class="body-tabla">${proveedor.rucProveedor}</td>
                                <td class="body-tabla">${proveedor.razonSocialProveedor}</td>
                                <td class="body-tabla">${proveedor.telefonoProveedor}</td>
                                <td class="body-tabla">${proveedor.correoProveedor}</td>
                                <td class="body-tabla">
                                    <a class="btn btn-secundario btn-editar">Editar</a>
                                    <a class="btn btn-secundario btn-eliminar">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="pestaña-contenido" id="contenido-agregarProveedores">

                <section class="form-grupo">
                    <form action="/gestion/adminProveedores/guardar" method="POST">
                        <div class="formulario">
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

                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Agregar Proveedor</button>
                        </div>
                    </form>
                </section>
            </div>

            <div class="pestaña-contenido" id="contenido-editarProveedores">

                <section class="form-grupo">
                    <label for="buscar-proveedor-editar" class="form-label">Buscar Proveedor (RUC o Razón Social)</label>
                    <div class="buscador-boton">
                        <input
                        type="text"
                        id="buscar-proveedor-editar"
                        class="form-control"
                        placeholder="Ej: Alicorp"
                        >
                        <button class="btn btn-primario">Buscar</button>
                    </div>
                </section>

                <section class="form-grupo">
                    <label class="form-label">Seleccione proveedor a editar</label>
                    <table class="tabla">
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
                        <tbody class="body-tabla">
                            <tr>
                                <td class="body-tabla">1</td>
                                <td class="body-tabla">20100055237</td>
                                <td class="body-tabla">Alicorp S.A.A.</td>
                                <td class="body-tabla">987654321</td>
                                <td class="body-tabla">ventas@alicorp.com.pe</td>
                                <td class="body-tabla">
                                    <a class="btn btn-secundario btn-elegir">Elegir</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </section>

                <section class="form-grupo">
                    <form action="/gestion/proveedores/actualizar" method="POST">
                        <input type="hidden" name="idProveedor" id="editar-id-proveedor">

                        <div class="formulario">
                            <div class="form-grupo">
                                <label for="editar-ruc-proveedor" class="form-label">RUC</label>
                                <input
                                    type="text"
                                    id="editar-ruc-proveedor"
                                    name="ruc"
                                    class="form-control"
                                    maxlength="11"
                                    required
                                >
                            </div>

                            <div class="form-grupo">
                                <label for="editar-razonsocial-proveedor" class="form-label">Razón Social</label>
                                <input
                                    type="text"
                                    id="editar-razonsocial-proveedor"
                                    name="razonSocial"
                                    class="form-control"
                                    required
                                >
                            </div>

                            <div class="form-grupo">
                                <label for="editar-telefono-proveedor" class="form-label">Teléfono</label>
                                <input
                                    type="text"
                                    id="editar-telefono-proveedor"
                                    name="telefono"
                                    class="form-control"
                                >
                            </div>

                            <div class="form-grupo">
                                <label for="editar-email-proveedor" class="form-label">Correo electrónico</label>
                                <input
                                    type="email"
                                    id="editar-email-proveedor"
                                    name="correo"
                                    class="form-control"
                                >
                            </div>
                        </div>

                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Guardar Cambios</button>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    </main>

    <%@ include file="../footer.jsp" %>

</body>
</html>