<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ALMXN - Sistema de Gestión de Almacén</title>
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>

<main class="login-contenedor">
    <article class="login-tarjeta">

        <a class="logo" href="/login">
            <img src="/img/logoGrandeALMXNAlt.png" alt="Logo ALMXN" class="img img-logo">
        </a>

        <div class="login-header">
            <h1 class="titulo-seccion">Iniciar Sesión</h1>
            <p class="subtitulo-seccion">Sistema de Gestión de Almacén</p>
        </div>

        <% if (request.getParameter("error") != null) { %>
        <p style="color:red; text-align:center; margin-bottom:10px;">
            Correo o contraseña incorrectos.
        </p>
        <% } %>

        <form action="/login" method="post" class="formulario">

            <div class="form-grupo">
                <label for="correo" class="form-label">Correo</label>
                <input type="text" id="correo" name="correo"
                       class="form-control" placeholder="Ingresa tu correo" required>
            </div>

            <div class="form-grupo">
                <label for="contraseña" class="form-label">Contraseña</label>
                <input type="password" id="contraseña" name="contraseña"
                       class="form-control" placeholder="Ingresa tu contraseña" required>
            </div>

            <button type="submit" class="btn btn-primario btn-block">Ingresar al sistema</button>
        </form>

    </article>
</main>

<script src="/js/validaciones.js"></script>
</body>
</html>