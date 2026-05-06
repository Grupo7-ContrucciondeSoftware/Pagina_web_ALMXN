<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Editar Producto - Sistema de Gestión de Almacén">
    <title>ALMXN - Editar Producto</title>

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

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Editar Producto</h1>
                    <p class="subtitulo-seccion">Modificando los datos del código: ${producto.codigoProducto}</p>
                </div>

                <!-- BOTON CANCELAR / VOLVER -->
                <div class="formulario-acciones">
                    <a href="/gestion/adminProductos" class="btn btn-secundario">Cancelar y Volver</a>
                </div>
            </section>

            <!-- FORMULARIO -->
            <div class="pestaña-contenido" style="display: block;">

                <section class="form-grupo">

                    <form action="/gestion/adminProductos/actualizar" method="POST">

                        <input type="hidden" name="idProducto" value="${producto.idProducto}">

                        <div class="formulario">

                            <!-- Nombre de producto -->
                            <div class="form-grupo">
                                <label for="nombre-producto" class="form-label">Nombre del producto</label>
                                <input
                                    type="text"
                                    id="nombre-producto"
                                    name="nombreProducto"
                                    class="form-control"
                                    value="${producto.nombreProducto}"
                                    required>
                            </div>

                            <!-- Categoría -->
                            <div class="form-grupo">
                                <label for="categoriaProducto" class="form-label">Categoría</label>
                                <select id="categoriaProducto" name="categoria.idCategoria" class="form-control" required>
                                    <option value="" disabled>Seleccione una categoría</option>
                                    <c:forEach items="${listaCategorias}" var="catProducto">
                                        <option value="${catProducto.idCategoria}"
                                            <c:if test="${catProducto.idCategoria == producto.categoria.idCategoria}">selected</c:if>>
                                            ${catProducto.nombreCategoria}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Unidad de Medida -->
                            <div class="form-grupo">
                                <label for="unidad-medida" class="form-label">Unidad de medida</label>
                                <select id="unidad-medida" name="unidadMedidaProducto" class="form-control" required>
                                    <option value="" disabled>Seleccione una unidad</option>
                                    <option value="unidad" <c:if test="${producto.unidadMedidaProducto == 'unidad'}">selected</c:if>>Unidad</option>
                                    <option value="kg" <c:if test="${producto.unidadMedidaProducto == 'kg'}">selected</c:if>>Kg</option>
                                    <option value="Bolsa" <c:if test="${producto.unidadMedidaProducto == 'Bolsa'}">selected</c:if>>Bolsa</option>
                                    <option value="litro" <c:if test="${producto.unidadMedidaProducto == 'litro'}">selected</c:if>>Litro (L)</option>
                                    <option value="Caja" <c:if test="${producto.unidadMedidaProducto == 'Caja'}">selected</c:if>>Caja</option>
                                    <option value="Paquete" <c:if test="${producto.unidadMedidaProducto == 'Paquete'}">selected</c:if>>Paquete</option>
                                </select>
                            </div>

                            <!-- Precio de Costo -->
                            <div class="form-grupo">
                                <label for="precio-costo" class="form-label">Precio de Costo</label>
                                <input
                                    type="number"
                                    step="any"
                                    id="precio-costo"
                                    name="precioCostoProducto"
                                    class="form-control"
                                    value="${producto.precioCostoProducto}"
                                    min="0"
                                    required>
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
                                    value="${producto.precioVentaProducto}"
                                    min="0"
                                    required>
                            </div>
                        </div>

                        <!-- Descripción -->
                        <div class="form-grupo form-textarea">
                            <label for="descripcion-producto" class="form-label">Descripción <span class="formulario-opcional">(opcional)</span></label>
                            <textarea
                                id="descripcion-producto"
                                name="descripcionProducto"
                                class="form-control"
                                rows="3">${producto.descripcionProducto}</textarea>
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