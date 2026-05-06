<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Administrar Productos - Sistema de Gestión de Almacén">
    <title>ALMXN - Administrar Productos</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminProductos.css">

</head>
<body>

    <!-- ===== HEADER ===== -->
    <%@ include file="/WEB-INF/views/header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <!-- ===== INPUTS PARA CAMBIAR DE PESTAÑAS ===== -->
        <input type="radio" name="tab" id="pestaña-listaProducto" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarProducto" style="display:none">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Administrar Productos</h1>
                    <p class="subtitulo-seccion">Gestione los productos del sistema</p>
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
                <label for="pestaña-listaProducto" class="pestaña">Lista de Productos</label>
                <label for="pestaña-agregarProducto" class="pestaña">Agregar Producto</label>
            </div>



            <!-- ============================================
                           LISTAR PRODUCTOS
            ============================================ -->



            <div class="pestaña-contenido" id="contenido-listaProducto">

                <!-- FILTROS -->
                <div class="filtro-container">

                    <!-- Nombre -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="nombre-filtro">Nombre: </label>
                        <input class="form-control" type="text" id="nombre-filtro" placeholder="Ej: Leche Gloria">
                    </div>

                    <!-- Categoría -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="categoria-filtro">Categoría:</label>
                        <select id="categoria-filtro" name="idCategoria" class="form-control">
                            <option value="" selected>Sin filtro</option>
                            <c:forEach items="${listaCategorias}" var="catProducto">
                                <option value="${catProducto.idCategoria}">${catProducto.nombreCategoria}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Stock -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="stockmin-filtro">Stock:</label>
                        <div class="filtro-doble">
                            <input class="form-control" type="number" id="stockmin-filtro" placeholder="Mín">
                            <input class="form-control" type="number" id="stockmax-filtro" placeholder="Máx">
                        </div>
                    </div>

                    <!-- Precio -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="preciomin-filtro">Precio:</label>
                        <div class="filtro-doble">
                            <input class="form-control" type="number" id="preciomin-filtro" placeholder="Mín">
                            <input class="form-control" type="number" id="preciomax-filtro" placeholder="Máx">
                        </div>
                    </div>

                    <!-- Fecha -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="fechaMin-filtro">Fecha: </label>
                        <div class="filtro-doble">
                            <input class="form-control" type="date" id="fechaMin-filtro">
                            <input class="form-control" type="date" id="fechaMax-filtro">
                        </div>
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
                        <button class="btn btn-secundario" id="btnFiltrar">Filtrar</button>
                        <button class="btn btn-secundario" id="btnLimpiar">Limpiar</button>
                    </div>

                </div>

                <!-- TABLA -->
                <table class="tabla">

                    <!-- HEADER DE LA TABLA -->
                    <thead class="header-tabla">
                        <tr>
                            <th class="header-tabla">Codigo</th>
                            <th class="header-tabla">Fecha de Adición</th>
                            <th class="header-tabla">Producto</th>
                            <th class="header-tabla">Categoría</th>
                            <th class="header-tabla">Stock Actual</th>
                            <th class="header-tabla">Unidad de medida</th>
                            <th class="header-tabla">Precio x Unidad</th>
                            <th class="header-tabla">Estado</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>

                    <!-- CONTENIDO DE LA TABLA -->
                    <tbody class="body-tabla">
                        <c:forEach var="producto" items="${listaProductos}">
                            <tr>
                                <td class="body-tabla">${producto.codigoProducto}</td>
                                <td class="body-tabla">${producto.fechaCreacionProducto}</td>
                                <td class="body-tabla">${producto.nombreProducto}</td>
                                <td class="body-tabla">${producto.categoria.nombreCategoria}</td>
                                <td class="body-tabla">${producto.stockActualProducto}</td>
                                <td class="body-tabla">${producto.unidadMedidaProducto}</td>
                                <td class="body-tabla">S/${producto.precioVentaProducto}</td>
                                <td class="body-tabla"><span class="estado ${producto.estadoProducto.toLowerCase()}" >${producto.estadoProducto}</span></td>
                                <td class="body-tabla">

                                <c:choose>

                                    <c:when test="${producto.estadoProducto == 'Activo'}">
                                        <c:choose>
                                            <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                                <a href="/gestion/adminProductos/editar?id=${producto.idProducto}" class="btn btn-secundario btn-editar">Editar</a>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="btn btn-secundario" onclick="abrirModalPermiso()" style="font-size: 13px;" >Editar</button>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${sessionScope.usuarioLogueado.rol == 'Admin'}">
                                                <button type="button" class="btn btn-secundario btn-eliminar"
                                                        data-id="${producto.idProducto}"
                                                        data-nombre="${producto.nombreProducto}"
                                                        data-action="/gestion/adminProductos/eliminar"
                                                        data-param="idProducto">
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
                                                <form action="/gestion/adminProductos/activar" method="POST" >
                                                    <input type="hidden" name="idProducto" value="${producto.idProducto}">
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
                           AGREGAR PRODUCTO
            ============================================ -->



            <div class="pestaña-contenido" id="contenido-agregarProducto">

                <!-- FORMULARIO -->
                <section class="form-grupo">

                    <form action="/gestion/adminProductos/guardar" method="POST" class="form-validable">
                        <div class="formulario">

                            <!-- Nombre de producto -->
                            <div class="form-grupo">
                                <label for="nombre-producto" class="form-label">Nombre del producto</label>
                                <input
                                    type="text"
                                    id="nombre-producto"
                                    name="nombreProducto"
                                    class="form-control"
                                    placeholder="Ej: Platano Bizcochito"
                                    required
                                >
                            </div>

                            <!-- Seleccionar Categoria -->
                            <div class="form-grupo">
                                <label for="categoriaProducto" class="form-label">Categoría</label>
                                <select id="categoriaProducto" name="categoria.idCategoria" class="form-control">
                                    <option value="" disabled selected>Seleccione una categoría</option>
                                    <c:forEach items="${listaCategorias}" var="catProducto">
                                        <option value="${catProducto.idCategoria}">${catProducto.nombreCategoria}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Unidad de Medida -->
                            <div class="form-grupo">
                                <label for="unidad-medida" class="form-label">Unidad de medida</label>
                                <select id="unidad-medida" name="unidadMedidaProducto" class="form-control">
                                    <option value="" disabled selected>Seleccione una unidad</option>
                                    <option value="unidad">Unidad</option>
                                    <option value="kg">Kg</option>
                                    <option value="Bolsa">Bolsa</option>
                                    <option value="litro">Litro (L)</option>
                                    <option value="Caja">Caja</option>
                                    <option value="Paquete">Paquete</option>
                                </select>
                            </div>

                            <!-- Precio de Costo -->
                            <div class="form-grupo">
                                <label for="precio-costo" class="form-label">Precio de Costo</label>
                                <input
                                    type="number"
                                    step="any""
                                    id="precio-costo"
                                    name="precioCostoProducto"
                                    class="form-control"
                                    placeholder="Ej: 40"
                                    min="0"
                                    required
                                >
                            </div>

                            <!-- Precio por Unidad -->
                            <div class="form-grupo">
                                <label for="precio-unidad" class="form-label">Precio por unidad</label>
                                <input
                                    type="number"
                                    step="any"
                                    id="precio-unidad"
                                    name="precioVentaProducto"
                                    class="form-control"
                                    placeholder="Ej: 10"
                                    min="0"
                                    required
                                >
                            </div>
                        </div>

                        <!-- Descripción -->
                        <div class="form-grupo form-textarea">
                            <label for="descripcion-producto" class="form-label">Descripción <span class="formulario-opcional">(opcional)</span></label>
                            <textarea
                                id="descripcion-producto"
                                name="descripcionProducto"
                                class="form-control"
                                placeholder="Descripción breve del producto..."
                                rows="3"
                            ></textarea>
                        </div>

                        <!-- Botones Guardar/Limpiar -->
                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Agregar Producto</button>
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
    <%@ include file="/WEB-INF/views/restriccion.jsp" %>

    <!-- ===== SCRIPTS ===== -->
    <script src="/js/tema.js" defer></script>
    <script src="/js/validaciones.js" defer></script>
    <script src="/js/Filtros/productosFiltro.js" defer></script>
    <script src="/js/Eliminar/eliminarFuncion.js" defer></script>

</body>
</html>