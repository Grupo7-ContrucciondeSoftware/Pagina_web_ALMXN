<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ALMXN - Administrar Productos</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminProductos.css">

    <script src="../js/tema.js" defer></script>
</head>
<body>

    <%@ include file="../header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <!-- ===== INPUTS PARA CAMBIAR DE PESTAÑAS ===== -->
        <input type="radio" name="tab" id="pestaña-listaProducto" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarProducto" style="display:none">
        <input type="radio" name="tab" id="pestaña-editarProducto" style="display:none">

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
                <label for="pestaña-editarProducto" class="pestaña">Editar Producto</label>
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
                        <select class="form-control" id="categoria-filtro">
                            <option value="">Sin Filtro</option>
                                <option value="Abarrotes">Abarrotes</option>
                                <option value="Lacteos y Huevos">Lácteos y Huevos</option>
                                <option value="Bebidas y Licores">Bebidas y Licores</option>
                                <option value="Snacks y Confiteria">Snacks y Confitería</option>
                                <option value="Frutas y Verduras">Frutras y Verduras</option>
                                <option value="Limpieza y Hogar">Limpieza y Hogar</option>
                                <option value="Cuidado Personal">Cuidado Personal</option>
                                <option value="Embutidos y Fríos">Embutidos y Fríos</option>
                                <option value="Panadería y Pastelería">Panadería y Pastelería</option>
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
                            <th class="header-tabla">Codigo</th>
                            <th class="header-tabla">Fecha de Adición</th>
                            <th class="header-tabla">Producto</th>
                            <th class="header-tabla">Categoría</th>
                            <th class="header-tabla">Stock Actual</th>
                            <th class="header-tabla">Unidad de medida</th>
                            <th class="header-tabla">Precio x Unidad</th>
                            <th class="header-tabla">Descripción</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>

                    <!-- CONTENIDO DE LA TABLA -->
                    <tbody class="body-tabla">
                        <c:forEach var="producto" items="${listaProductos}">
                            <tr>
                                <td class="body-tabla">${producto.idProducto}</td>
                                <td class="body-tabla">${producto.codigoProducto}</td>
                                <td class="body-tabla">${producto.fechaCreacionProducto}</td>
                                <td class="body-tabla">${producto.nombreProducto}</td>
                                <td class="body-tabla">${producto.categoria.nombreCategoria}</td>
                                <td class="body-tabla">${producto.stockActualProducto}</td>
                                <td class="body-tabla">${producto.unidadMedidaProducto}</td>
                                <td class="body-tabla">S/${producto.precioVentaProducto}</td>
                                <td class="body-tabla">${producto.descripcionProducto}</td>
                                <td class="body-tabla">
                                    <a class="btn btn-secundario btn-editar">Editar</a>
                                    <a class="btn btn-secundario btn-eliminar">Eliminar</a>
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

                    <div class="formulario">

                        <!-- Código -->
                        <div class="form-grupo">
                            <label for="codigo-producto" class="form-label">Código</label>
                            <input
                                type="number"
                                id="codigp-producto"
                                name="codigoProducto"
                                class="form-control"
                                placeholder="Escriba el SKU"
                                min="0"
                                required
                            >
                        </div>

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

                        <!-- SELECCIONAR CATEGORÍA -->

                        <div class="form-grupo">
                            <label for="categoriaProducto" class="form-label">Categoría</label>
                            <select id="categoriaProducto" name="idCategoria" class="form-control">
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
                                <option value="kg">Kilogramo (kg)</option>
                                <option value="litro">Litro (L)</option>
                                <option value="caja">Caja</option>
                                <option value="paquete">Paquete</option>
                            </select>
                        </div>

                        <!-- Stock inicial -->
                        <div class="form-grupo">
                            <label for="stock-actual" class="form-label">Stock inicial</label>
                            <input
                                type="number"
                                id="stock-actual"
                                name="stockActualProducto"
                                class="form-control"
                                placeholder="Ej: 70"
                                min="0"
                                required
                            >
                        </div>

                        <!-- Stock mínimo -->
                        <div class="form-grupo">
                            <label for="stock-minimo" class="form-label">Stock mínimo</label>
                            <input
                                type="number"
                                id="stock-minimo"
                                name="stockMinimoProducto"
                                class="form-control"
                                placeholder="Ej: 10"
                                min="0"
                                required
                            >
                        </div>

                        <!-- Precio de Costo -->
                        <div class="form-grupo">
                            <label for="precio-costo" class="form-label">Precio de Costo</label>
                            <input
                                type="number"
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
                </section>
            </div>


            <!-- ============================================
                            EDITAR PRODUCTO
            ============================================ -->


            <div class="pestaña-contenido" id="contenido-editarProducto">

                <!-- Nombre de Producto -->
                <section class="form-grupo">

                    <label for="nombre-producto-ingreso" class="form-label">Nombre del producto</label>
                    <div class="buscador-boton">
                        <input
                        type="text"
                        id="nombre-producto-ingreso"
                        name="nombre-producto-ingreso"
                        class="form-control"
                        placeholder="Ej: Leche Gloria"
                        required
                        >
                        <button class="btn btn-primario">Buscar</button>
                    </div>
                </section>

                <!-- TABLA -->
                <section class="form-grupo">
                    <label class="form-label">Seleccione producto</label>

                    <table class="tabla">
                        <thead class="header-tabla">
                            <tr>
                                <th class="header-tabla">Codigo</th>
                                <th class="header-tabla">Producto</th>
                                <th class="header-tabla">Categoría</th>
                                <th class="header-tabla">Stock Actual</th>
                                <th class="header-tabla">Unidad de medida</th>
                                <th class="header-tabla">Precio x Unidad</th>
                                <th class="header-tabla">Descripción</th>
                                <th class="header-tabla">Acciones</th>
                            </tr>
                        </thead>
                        <tbody class="body-tabla">
                            <tr>
                                <td class="body-tabla">1</td>
                                <td class="body-tabla">Lata Leche Gloria</td>
                                <td class="body-tabla">Lácteos</td>
                                <td class="body-tabla">10</td>
                                <td class="body-tabla">Unidad</td>
                                <td class="body-tabla">S/3.90</td>
                                <td class="body-tabla">Leche entera</td>
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
                                <td class="body-tabla">-</td>
                            </tr>
                        </tbody>
                    </table>
                </section>

                <!-- FORMULARIO -->
                <section class="form-grupo">

                    <div class="formulario">

                        <!-- Código -->
                        <div class="form-grupo">
                            <label for="codigo-producto" class="form-label">Código</label>
                            <input
                                type="number"
                                id="codigo-producto"
                                name="codigo-producto"
                                class="form-control"
                                placeholder="Escriba el código"
                                min="0"
                                required
                            >
                        </div>

                        <!-- Nombre de producto -->
                        <div class="form-grupo">
                            <label for="nombre-producto" class="form-label">Nombre del producto</label>
                            <input
                                type="text"
                                id="nombre-producto"
                                name="nombre-producto"
                                class="form-control"
                                placeholder="Ej: Platano Isla"
                                required
                            >
                        </div>

                        <!-- Categoría -->
                        <div class="form-grupo">
                            <label for="categoria-producto" class="form-label">Categoría</label>
                            <select id="categoria-producto" name="categoria-producto" class="form-control">
                                <option value="" disabled selected>Seleccione una categoría</option>
                                <option value="Abarrotes">Abarrotes</option>
                                <option value="Lacteos y Huevos">Lácteos y Huevos</option>
                                <option value="Bebidas y Licores">Bebidas y Licores</option>
                                <option value="Snacks y Confiteria">Snacks y Confitería</option>
                                <option value="Frutas y Verduras">Frutras y Verduras</option>
                                <option value="Limpieza y Hogar">Limpieza y Hogar</option>
                                <option value="Cuidado Personal">Cuidado Personal</option>
                                <option value="Embutidos y Fríos">Embutidos y Fríos</option>
                                <option value="Panadería y Pastelería">Panadería y Pastelería</option>
                            </select>
                        </div>

                        <!-- Unidad de Medida -->
                        <div class="form-grupo">
                            <label for="unidad-medida" class="form-label">Unidad de medida</label>
                            <select id="unidad-medida" name="unidad-medida" class="form-control">
                                <option value="" disabled selected>Seleccione una unidad</option>
                                <option value="unidad">Unidad</option>
                                <option value="kg">Kilogramo (kg)</option>
                                <option value="litro">Litro (L)</option>
                                <option value="caja">Caja</option>
                                <option value="paquete">Paquete</option>
                            </select>
                        </div>

                        <!-- Stock mínimo -->
                        <div class="form-grupo">
                            <label for="stock-minimo" class="form-label">Stock mínimo</label>
                            <input
                                type="number"
                                id="stock-minimo"
                                name="stock-minimo"
                                class="form-control"
                                placeholder="Ej: 10"
                                min="0"
                                required
                            >
                        </div>

                        <!-- Precio Unidad -->
                        <div class="form-grupo">
                            <label for="precio-unidad" class="form-label">Precio por unidad</label>
                            <input
                                type="number"
                                id="precio-unidad"
                                name="precio-unidad"
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
                                name="descripcion-producto"
                                class="form-control"
                                placeholder="Descripción breve del producto..."
                                rows="3"
                            ></textarea>
                    </div>

                    <!-- Botones Guardar/Limpiar -->
                    <div class="formulario-acciones">
                        <button type="reset" class="btn btn-secundario">Limpiar</button>
                        <button type="submit" class="btn btn-primario">Guardar cambios</button>
                    </div>

                </section>

            </div>

        </div>

    </main>

    <!-- ===== PIE DE PÁGINA ===== -->
    <%@ include file="../footer.jsp" %>

</body>
</html>