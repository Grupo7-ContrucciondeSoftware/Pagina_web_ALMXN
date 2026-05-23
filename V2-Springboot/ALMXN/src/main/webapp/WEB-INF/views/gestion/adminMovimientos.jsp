<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Administrar Movimientos - Sistema de Gestión de Almacén">
    <title>ALMXN - Administrar Movimientos</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/globalGestion.css">
    <link rel="stylesheet" href="/css/adminUsuarios.css">
    <link rel="stylesheet" href="/css/adminMovimientos.css">

</head>
<body>

    <!-- ===== HEADER ===== -->
    <%@ include file="/WEB-INF/views/header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <!-- ===== INPUTS PARA CAMBIAR DE PESTAÑAS ===== -->
        <input type="radio" name="tab" id="pestaña-salida" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-ingreso" style="display:none">
        <input type="radio" name="tab" id="pestaña-historial" style="display:none">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <div>
                    <h1 class="titulo-seccion">Administrar Movimientos</h1>
                    <p class="subtitulo-seccion">Registra ingresos, salidas y consulta el historial</p>
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
                <label for="pestaña-salida" class="pestaña">Registrar Salida</label>
                <label for="pestaña-ingreso" class="pestaña">Registrar Ingreso</label>
                <label for="pestaña-historial" class="pestaña">Historial de Movimientos</label>
            </div>



            <!-- ============================================
                           REGISTRAR SALIDAS
            ============================================ -->


            <div class="pestaña-contenido" id="contenido-salida">

                <!-- FORMULARIO -->
                <form action="/gestion/adminMovimientos/registrarMovimiento" method="POST">

                    <input type="hidden" name="tipoMovimiento" value="Salida">
                    <section class="gestion-inicio">
                        <div class="formulario">

                            <!-- Fecha Movimiento -->
                            <div class="form-grupo">
                                <label for="fecha-salida" class="form-label">Fecha del Movimiento</label>
                                <input
                                    type="date"
                                    id="fecha-salida"
                                    name="fechaMovimiento"
                                    class="form-control"
                                    required>
                            </div>

                            <!-- Motivo Salida -->
                            <div class="form-grupo">
                                <label for="motivo-salida" class="form-label">Motivo de Salida</label>
                                <select id="motivo-salida" name="motivoMovimiento" class="form-control" required>
                                    <option value="" disabled selected>Seleccione motivo</option>
                                    <option value="Despacho">Despacho</option>
                                    <option value="Merma">Merma</option>
                                    <option value="Devolución">Devolución a Proveedor</option>
                                </select>
                            </div>

                            <!-- Destino -->
                            <div class="form-grupo">
                                <label for="destino-salida" class="form-label">Destino (Opcional)</label>
                                <input
                                    type="text"
                                    id="destino-salida"
                                    name="destinoMovimiento"
                                    class="form-control"
                                    placeholder="Ej: Tienda Huancayo">
                            </div>
                        </div>

                        <!-- Observaciones -->
                        <div class="form-grupo form-textarea">
                            <label for="observacion-salida" class="form-label">Observaciones Generales (Opcional)</label>
                            <textarea
                                id="observacion-salida"
                                name="observacionesMovimiento"
                                class="form-control"
                                rows="2"
                                placeholder="Escriba una descripción.."
                            ></textarea>
                        </div>
                    </section>

                    <!-- LISTA DE PRODUCTOS -->
                    <section class="form-grupo">
                        <p class="form-label lista">Añadir Productos a la Lista</p>

                        <div class="buscador-boton">
                            <input type="text" id="busqueda-prod-salida" class="form-control" placeholder="Buscar producto...">
                            <button type="button" id="btn-buscar-prod-salida" class="btn btn-primario">Buscar</button>
                        </div>

                        <!-- Tabla -->
                        <table class="tabla" >
                            <thead class="header-tabla">
                                <tr>
                                    <th class="header-tabla">Código</th>
                                    <th class="header-tabla">Producto</th>
                                    <th class="header-tabla">Stock</th>
                                    <th class="header-tabla">Precio de Venta</th>
                                    <th class="header-tabla">Cantidad</th>
                                    <th class="header-tabla">Acción</th>
                                </tr>
                            </thead>
                            <tbody class="body-tabla" id="tbody-resultados-salida">
                                <tr>
                                    <td>---</td>
                                    <td>---</td>
                                    <td>---</td>
                                    <td>---</td>
                                    <td><input type="number" class="form-control cantidad" value="1" min="1"></td>
                                    <td><button type="button" class="btn btn-secundario">+ Añadir</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </section>

                    <!-- LISTA DE SALIDAS -->
                    <section class="form-grupo">
                        <p class="form-label lista">Productos en este Movimiento</p>
                        <table class="tabla">
                            <thead class="header-tabla">
                                <tr>
                                    <th class="header-tabla">Código</th>
                                    <th class="header-tabla">Producto</th>
                                    <th class="header-tabla">Cantidad</th>
                                    <th class="header-tabla">Precio de Venta Unitario</th>
                                    <th class="header-tabla">Subtotal</th>
                                    <th class="header-tabla">Acciones</th>
                                </tr>
                            </thead>
                            <tbody class="body-tabla" id="tabla-detalles-salida" >
                                <tr class="fila-vacia">
                                    <td  colspan="6" class="producto-lista" >
                                        No hay productos en la lista. Use el buscador de arriba.
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4" class="total-lista"><strong>TOTAL DEL MOVIMIENTO:</strong></td>
                                    <td colspan="2" class="total-lista-precio"><span class="total-movimiento" style="">S/ 0.00</span></td>
                                </tr>
                            </tfoot>
                        </table>

                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Cancelar Todo</button>
                            <button type="submit" class="btn btn-primario">Confirmar y Registrar Salida</button>
                        </div>
                    </section>

                </form>
            </div>



            <!-- ============================================
                           REGISTRAR INGRESOS
            ============================================ -->


            <div class="pestaña-contenido" id="contenido-ingreso">

                <!-- FORMULARIO -->
                <form action="/gestion/adminMovimientos/registrarMovimiento" method="POST">

                    <input type="hidden" name="tipoMovimiento" value="Ingreso">
                    <section class="gestion-inicio">
                        <div class="formulario">

                            <!-- Fecha Movimiento -->
                            <div class="form-grupo">
                                <label for="fecha-ingreso" class="form-label">Fecha del Movimiento</label>
                                <input
                                    type="date"
                                    id="fecha-ingreso"
                                    name="fechaMovimiento"
                                    class="form-control"
                                    required>
                            </div>

                            <!-- Motivo Ingreso -->
                             <div class="form-grupo">
                                <label for="motivo-ingreso" class="form-label">Motivo de Ingreso</label>
                                <select id="motivo-ingreso" name="motivoMovimiento" class="form-control">
                                    <option value="" disabled selected>Seleccione motivo</option>
                                    <option value="Compra">Compra</option>
                                    <option value="Devolucion">Devolución</option>
                                    <option value="Otros">Otros</option>
                                </select>
                            </div>

                            <!-- Seleccionar Proveedor -->
                            <div class="form-grupo">
                                <label for="proveedor-ingreso" class="form-label">Proveedor</label>
                                <select id="proveedor-ingreso" name="proveedor.idProveedor" class="form-control" required>
                                    <option value="" disabled selected>Seleccione un proveedor</option>
                                    <c:forEach items="${listaProveedores}" var="proveedorIngreso">
                                        <option value="${proveedorIngreso.idProveedor}">${proveedorIngreso.razonSocialProveedor}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <!-- Observaciones -->
                        <div class="form-grupo form-textarea">
                            <label for="observacion-ingreso" class="form-label">Observaciones Generales (Opcional)</label>
                            <textarea
                                id="observacion-ingreso"
                                name="observacionesMovimiento"
                                class="form-control"
                                rows="2"
                                placeholder="Escriba una descripción.."
                            ></textarea>
                        </div>
                    </section>

                    <!-- LISTA DE PRODUCTOS -->
                    <section class="form-grupo">
                        <p class="form-label lista">Añadir Productos a la Lista</p>

                        <div class="buscador-boton">
                            <input type="text" id="busqueda-prod-ingreso" class="form-control" placeholder="Buscar producto...">
                            <button type="button" id="btn-buscar-prod-ingreso" class="btn btn-primario">Buscar</button>
                        </div>

                        <!-- Tabla -->
                        <table class="tabla" >
                            <thead class="header-tabla">
                                <tr>
                                    <th class="header-tabla">Código</th>
                                    <th class="header-tabla">Producto</th>
                                    <th class="header-tabla">Precio de Coste</th>
                                    <th class="header-tabla">Cantidad</th>
                                    <th class="header-tabla">Acción</th>
                                </tr>
                            </thead>
                            <tbody class="body-tabla" id="tbody-resultados-ingreso">
                                <tr>
                                    <td>---</td>
                                    <td>---</td>
                                    <td>---</td>
                                    <td><input type="number" class="form-control cantidad" value="1" min="1"></td>
                                    <td><button type="button" class="btn btn-secundario">+ Añadir</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </section>

                    <!-- LISTA DE INGRESOS -->
                    <section class="form-grupo">
                        <p class="form-label lista">Productos en este Movimiento</p>
                        <table class="tabla">
                            <thead class="header-tabla">
                                <tr>
                                    <th class="header-tabla">Código</th>
                                    <th class="header-tabla">Producto</th>
                                    <th class="header-tabla">Cantidad</th>
                                    <th class="header-tabla">Precio de Coste Unitario</th>
                                    <th class="header-tabla">Subtotal</th>
                                    <th class="header-tabla">Acciones</th>
                                </tr>
                            </thead>
                            <tbody class="body-tabla" id="tabla-detalles-ingreso">
                                <tr class="fila-vacia">
                                    <td colspan="6" class="producto-lista" >
                                        No hay productos en la lista. Use el buscador de arriba.
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4" class="total-lista"><strong>TOTAL DEL MOVIMIENTO:</strong></td>
                                    <td colspan="2" class="total-lista-precio"><span class="total-movimiento" style="">S/ 0.00</span></td>
                                </tr>
                            </tfoot>
                        </table>

                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Cancelar Todo</button>
                            <button type="submit" class="btn btn-primario">Confirmar y Registrar Salida</button>
                        </div>
                    </section>

                </form>
            </div>



            <!-- ============================================
                           HISTORIAL DE MOVIMIENTOS
            ============================================ -->



            <div class="pestaña-contenido" id="contenido-historial">

                <!-- FILTROS -->
                <div class="filtro-container">

                    <!-- Tipo Movimieto -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="tipo-filtro">Tipo:</label>
                        <select class="form-control" id="tipo-filtro">
                            <option value="">Todos</option>
                            <option value="Ingreso">Ingreso</option>
                            <option value="Salida">Salida</option>
                        </select>
                    </div>

                    <!-- Origen/Destino -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="origen-filtro">Origen / Destino:</label>
                        <input class="form-control" type="text" id="origen-filtro" placeholder="Ej: Alicorp, Almacén 2">
                    </div>

                    <!-- Responsable -->
                    <div class="filtro-grupo">
                        <label class="form-label" for="usuario-filtro">Responsable:</label>
                        <select id="usuario-filtro" name="idUsuarios" class="form-control">
                            <option value="" selected>Todos los usuarios</option>
                            <c:forEach items="${listaUsuarios}" var="usuarioFiltro">
                                <option value="${usuarioFiltro.idUsuario}">${usuarioFiltro.nombres} ${usuarioFiltro.apellidos}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Fechas -->
                    <div class="filtro-grupo">
                        <label class="form-label">Rango de Fechas: </label>
                        <div class="filtro-doble">
                            <input class="form-control fechaFiltro" type="date" id="fechaMin-filtro" title="Desde">
                            <input class="form-control fechaFiltro" type="date" id="fechaMax-filtro" title="Hasta">
                        </div>
                    </div>

                    <!-- Botones -->
                    <div class="filtro-acciones">
                        <button class="btn btn-secundario" id="btnFiltrar">Filtrar</button>
                        <button class="btn btn-secundario" id="btnLimpiar">Limpiar</button>
                    </div>

                </div>

                <table class="tabla">
                    <thead class="header-tabla">
                        <tr>
                            <th class="header-tabla">ID Mov.</th>
                            <th class="header-tabla">Fecha</th>
                            <th class="header-tabla">Tipo</th>
                            <th class="header-tabla">Responsable</th>
                            <th class="header-tabla">Origen / Destino</th>
                            <th class="header-tabla">Motivo</th>
                            <th class="header-tabla">Total (S/)</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>
                    <tbody class="body-tabla">
                        <c:forEach var="mov" items="${listaMovimientos}">
                            <tr>
                                <td class="body-tabla">#${mov.idMovimiento}</td>
                                <td class="body-tabla">${mov.fechaMovimiento}</td>
                                <td class="body-tabla">
                                    <span class="tipo-${mov.tipoMovimiento.toLowerCase()}" >
                                        ${mov.tipoMovimiento}
                                    </span>
                                </td>
                                <td class="body-tabla">
                                    <span class="rol rol-${mov.usuario.rol.toLowerCase()}">
                                        ${mov.usuario.nombres} ${mov.usuario.apellidos}
                                    </span>
                                </td>
                                <td class="body-tabla">${mov.proveedor.razonSocialProveedor}</td>
                                <td class="body-tabla">${mov.motivoMovimiento}</td>
                                <td class="body-tabla">${mov.totalMovimiento}</td>
                                <td class="body-tabla">
                                    <button class="btn btn-primario btn-ver-detalle"
                                            data-id="${mov.idMovimiento}"
                                            data-tipo="${mov.tipoMovimiento}"
                                            data-fecha="${mov.fechaMovimiento}"
                                            data-responsable="${mov.usuario.nombres} ${mov.usuario.apellidos}"
                                            data-origen="${mov.proveedor.razonSocialProveedor != null ? mov.proveedor.razonSocialProveedor : mov.destinoMovimiento}"
                                            data-motivo="${mov.motivoMovimiento}"
                                            data-observaciones="${mov.observacionesMovimiento}"
                                            data-total="${mov.totalMovimiento}">
                                        Ver Detalles
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>


            <!-- ============================================
                        MODAL DETALLE MOVIMIENTO
            ============================================ -->


            <div id="modal-detalle-movimiento" class="modal-oculto-detalle">

                <div class="modal-contenedor-detalle">

                    <div class="modal-header-detalle">
                        <h2>Detalle de Movimiento <span id="detalle-id-mov">#001</span></h2>
                        <button id="btn-cerrar-modal-superior" class="btn-cerrar-modal" >&times;</button>
                    </div>

                    <div class="modal-body-detalle">

                        <section class="modal-info-detalle" >
                            <div>
                                <p><strong>Tipo:</strong> <span id="detalle-tipo">-</span></p>
                                <p><strong>Fecha y Hora:</strong> <span id="detalle-fecha">-</span></p>
                                <p><strong>Responsable:</strong> <span id="detalle-responsable">-</span></p>
                            </div>
                            <div>
                                <p><strong>Origen/Destino:</strong> <span id="detalle-origen">-</span></p>
                                <p><strong>Motivo:</strong> <span id="detalle-motivo">-</span></p>
                                <p><strong>Observaciones:</strong> <span id="detalle-obs">-</span></p>
                            </div>
                        </section>

                        <h3 class="form-label">Productos Involucrados</h3>
                        <table class="tabla">
                            <thead class="header-tabla">
                                <tr>
                                    <th class="header-tabla">Código</th>
                                    <th class="header-tabla">Producto</th>
                                    <th class="header-tabla">Cantidad</th>
                                    <th class="header-tabla">Precio Unit.</th>
                                    <th class="header-tabla">Subtotal</th>
                                </tr>
                            </thead>
                            <tbody class="body-tabla" id="detalle-tabla-cuerpo">

                            </tbody>

                            <tfoot>
                                <tr>
                                    <td colspan="4" class="total-lista"><strong>TOTAL:</strong></td>
                                    <td><strong id="detalle-total-dinero" class="total-lista-precio">S/ 0.00</strong></td>
                                </tr>
                            </tfoot>
                        </table>

                    </div>

                    <div class="modal-acciones-detalle">
                        <button class="btn btn-secundario" id="btn-cerrar-modal-inferior">Cerrar</button>
                        <button class="btn btn-primario">Imprimir Recibo</button>
                    </div>

                </div>
            </div>

        </div>

    </main>

    <!-- ===== FOOTER ===== -->
    <%@ include file="/WEB-INF/views/footer.jsp" %>

    <!-- ===== SCRIPTS ===== -->
    <script src="/js/tema.js" defer></script>
    <script src="/js/movimientos.js" defer></script>
    <script src="/js/Filtros/movimientosFiltro.js" defer></script>

</body>
</html>