<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<input type="checkbox" id="theme-toggle" style="display:none">

    <!-- ===== ENCABEZADO ===== -->
    <header id="header-principal">
        <div class="header-contenedor">

            <!-- LOGO DEL SISTEMA -->
            <a class="logo" href="/main">
                <img src="/img/logoPequeñoALMXNAlt.png" alt="Logo ALMXN" class="img img-logo">
            </a>

            <!-- BARRA DE NAVEGACIÓN -->
            <nav id="nav-principal">
                <ul class="nav-lista">
                    <li class="nav-item">
                        <a href="/main" class="nav-enlace ${paginaActiva == 'inicio' ? 'nav-enlace--activo' : ''}">Inicio</a>
                    </li>

                    <li class="nav-item">
                        <a href="/gestion" class="nav-enlace ${paginaActiva == 'gestion' ? 'nav-enlace--activo' : ''}">Gestión</a>
                    </li>

                    <li class="nav-item">
                        <a href="/contacto" class="nav-enlace ${paginaActiva == 'contacto' ? 'nav-enlace--activo' : ''}">Contacto</a>
                    </li>

                    <li class="nav-item">
                        <a href="/publicidad" class="nav-enlace ${paginaActiva == 'publicidad' ? 'nav-enlace--activo' : ''}">Publicidad</a>
                    </li>


                </ul>
            </nav>

            <!-- USUARIO ACTUAL -->
            <div class="header-usuario">
                <span class="login-nombre">${usuarioLogueado.nombres} ${usuarioLogueado.apellidos}</span>
                <span class="login-rol ${usuarioLogueado.rol.toLowerCase()}-rol"><b>${usuarioLogueado.rol}</b></span>
                <a href="/login" class="btn btn-secundario">Cerrar sesión</a>
            </div>

            <label for="theme-toggle" class="btn-tema">
                    <img src="/img/modoOscuro.svg" class="icono-oscuro" alt="Modo Oscuro" style="min-height: 25px;">
                    <img src="/img/modoClaro.svg" class="icono-claro" alt="Modo claro" style="min-height: 25px;">
            </label>

        </div>

    </header>