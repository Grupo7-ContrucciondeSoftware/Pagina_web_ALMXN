<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Sistema de Gestión de Almacén - Panel principal">
    <title>ALMXN - Sistema de Gestión de Almacén</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/main.css">

    <script src="/js/tema.js" defer></script>

</head>

<body>

    <%@ include file="header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal">

        <!-- IMAGEN -->
        <section class="seccion-imagen">
            <img class="img img-main"
                src="https://t3.ftcdn.net/jpg/01/79/91/78/360_F_179917813_5wzagab80xZ8QOCE9QQsLNsFrtLh3EEy.jpg">
        </section>

        <div class="centro-pagina">

            <!-- TITULO/INICIO -->
            <section class="seccion-bienvenida">
                <h1 class="titulo-seccion">Bienvenido al Sistema de Gestión de Almacén</h1>
                <p class="subtitulo-seccion">
                    Controla el stock, registra movimientos y administra usuarios desde un solo lugar.
                </p>
            </section>

            <!-- CUERPO -->
            <div class="buscador-global">
                <h1 class="titulo-seccion">¿Qué producto buscas hoy?</h1>
                <p class="subtitulo-seccion">Ingresa el nombre o código del producto</p>

                <form action="/gestion/adminProductos" method="get" class="buscador-formulario">
                    <div class="buscador-boton">
                        <input type="text" id="busquedaGlobal" name="busqueda" class="form-control"
                               placeholder="Ej: Arroz Costeño 1kg ..." required>
                        <button type="submit" class="btn btn-primario btn-buscar">Buscar</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- IMAGEN -->
        <section class="seccion-imagen">
            <img class="img img-main" src="https://images.unsplash.com/photo-1586528116311-ad8dd3c8310d">
        </section>

    </main>

    <!-- ===== PIE DE PÁGINA ===== -->
    <%@ include file="footer.jsp" %>

</body>

</html>