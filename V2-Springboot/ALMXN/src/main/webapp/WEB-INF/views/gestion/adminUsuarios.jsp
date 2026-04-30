<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ALMXN - Administrar Usuarios</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="../css/global.css">
    <link rel="stylesheet" href="../css/globalGestion.css">
    <link rel="stylesheet" href="../css/adminUsuarios.css">

    <script src="../js/tema.js" defer></script>

    <!-- ===== ESTILOS DE VALIDACIÓN ===== -->
    <style>
        .form-control.error {
            border-color: #e53e3e;
            box-shadow: 0 0 0 2px rgba(229, 62, 62, 0.2);
        }
        .form-control.success {
            border-color: #38a169;
            box-shadow: 0 0 0 2px rgba(56, 161, 105, 0.15);
        }
        .campo-error {
            color: #e53e3e;
            font-size: 0.78rem;
            margin-top: 4px;
            display: none;
        }
        .campo-error.visible {
            display: block;
        }
        /* Indicador de fortaleza de contraseña */
        .fortaleza-barra {
            height: 4px;
            border-radius: 2px;
            margin-top: 5px;
            transition: width 0.3s, background-color 0.3s;
            width: 0%;
        }
        .fortaleza-texto {
            font-size: 0.75rem;
            margin-top: 2px;
        }
    </style>

</head>
<body>

    <%@ include file="../header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contenido-principal-gestion">

        <!-- ===== INPUTS PARA CAMBIAR DE PESTAÑAS ===== -->
        <input type="radio" name="tab" id="pestaña-listaUsuario" checked style="display:none">
        <input type="radio" name="tab" id="pestaña-agregarUsuario" style="display:none">
        <input type="radio" name="tab" id="pestaña-editarUsuario" style="display:none">

        <div class="centro-pagina-gestion">

            <!-- TITULO/INICIO -->
            <section class="gestion-inicio">
                <h1 class="titulo-seccion">Administrar Usuarios</h1>
                <p class="subtitulo-seccion">Gestione los usuarios del sistema</p>
                <div class="formulario-acciones">
                    <a href="/gestion" class="btn btn-secundario">Volver</a>
                </div>
            </section>


            <!-- ============================================
                           PESTAÑAS
            ============================================ -->
            <div class="pestañas">
                <label for="pestaña-listaUsuario" class="pestaña">Lista de Usuario</label>
                <label for="pestaña-agregarUsuario" class="pestaña">Agregar Usuario</label>
                <label for="pestaña-editarUsuario" class="pestaña">Editar Usuario</label>
            </div>


            <!-- ============================================
                           LISTAR USUARIO
            ============================================ -->
            <div class="pestaña-contenido" id="contenido-listaUsuario">

                <!-- FILTROS -->
                <div class="filtro-container">

                    <div class="filtro-grupo">
                        <label class="form-label" for="nombre-filtro">Nombre: </label>
                        <input class="form-control" type="text" id="nombre-filtro" placeholder="Ingrese nombre">
                    </div>

                    <div class="filtro-grupo">
                        <label class="form-label" for="rol-filtro">Rol:</label>
                        <select class="form-control" id="rol-filtro">
                            <option value="">Sin Filtro</option>
                            <option value="Administrador">Administrador</option>
                            <option value="Usuario">Usuario</option>
                        </select>
                    </div>

                    <div class="filtro-grupo">
                        <label class="form-label" for="estado-filtro">Estado:</label>
                        <select class="form-control" id="estado-filtro">
                            <option value="">Sin Filtro</option>
                            <option value="Activo">Activo</option>
                            <option value="Bloqueado">Bloqueado</option>
                            <option value="Suspendido">Suspendido</option>
                        </select>
                    </div>

                    <div class="filtro-grupo">
                        <label class="form-label">Fecha: </label>
                        <div class="filtro-doble">
                            <input class="form-control" type="date" id="fechaMin-filtro">
                            <input class="form-control" type="date" id="fechaMax-filtro">
                        </div>
                    </div>

                    <div class="filtro-acciones">
                        <button class="btn btn-secundario" id="btnFiltrar">Filtrar</button>
                        <button class="btn btn-secundario" id="btnLimpiar">Limpiar</button>
                    </div>

                </div>

                <!-- TABLA -->
                <table class="tabla">
                    <thead class="header-tabla">
                        <tr>
                            <th class="header-tabla">ID</th>
                            <th class="header-tabla">Nombre</th>
                            <th class="header-tabla">Correo</th>
                            <th class="header-tabla">Fecha Creación</th>
                            <th class="header-tabla">Rol</th>
                            <th class="header-tabla">Estado</th>
                            <th class="header-tabla">Acciones</th>
                        </tr>
                    </thead>
                    <tbody class="body-tabla">
                        <c:forEach var="usuario" items="${listaUsuarios}">
                            <tr>
                                <td class="body-tabla"><c:out value="${usuario.idUsuario}"/></td>
                                <td class="body-tabla"><c:out value="${usuario.nombres}"/></td>
                                <td class="body-tabla"><c:out value="${usuario.correo}"/></td>
                                <td class="body-tabla"><c:out value="${usuario.fechaCreacion}"/></td>
                                <td class="body-tabla">
                                    <span class="rol ${usuario.rol.toLowerCase()}">
                                        <c:out value="${usuario.rol}"/>
                                    </span>
                                </td>
                                <td class="body-tabla">
                                    <span class="estado ${usuario.estado.toLowerCase()}">
                                        <c:out value="${usuario.estado}"/>
                                    </span>
                                </td>
                                <td class="body-tabla">
                                    <a class="btn btn-secundario" id="btn-editar">Editar</a>
                                    <a class="btn btn-secundario" id="btn-eliminar">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>


            <!-- ============================================
                           AGREGAR USUARIO
            ============================================ -->
            <div class="pestaña-contenido" id="contenido-agregarUsuario">
                <section class="form-grupo">

                    <form id="form-agregar-usuario" action="/gestion/adminUsuarios/guardar" method="POST" novalidate>
                        <div class="formulario">

                            <!-- Nombres -->
                            <div class="form-grupo">
                                <label for="agr-nombres" class="form-label">Nombres</label>
                                <input type="text" id="agr-nombres" name="nombres" class="form-control"
                                       placeholder="Ej: Juan Eduardo" required>
                                <span class="campo-error" id="agr-error-nombres"></span>
                            </div>

                            <!-- Apellidos -->
                            <div class="form-grupo">
                                <label for="agr-apellidos" class="form-label">Apellidos</label>
                                <input type="text" id="agr-apellidos" name="apellidos" class="form-control"
                                       placeholder="Ej: Ramos Pérez" required>
                                <span class="campo-error" id="agr-error-apellidos"></span>
                            </div>

                            <!-- Correo -->
                            <div class="form-grupo">
                                <label for="agr-correo" class="form-label">Correo electrónico</label>
                                <input type="email" id="agr-correo" name="correo" class="form-control"
                                       placeholder="Ej: juan@email.com" required>
                                <span class="campo-error" id="agr-error-correo"></span>
                            </div>

                            <!-- Rol -->
                            <div class="form-grupo">
                                <label for="agr-rol" class="form-label">Rol</label>
                                <select id="agr-rol" name="rol" class="form-control" required>
                                    <option value="" disabled selected>Seleccione un rol</option>
                                    <option value="Admin">Administrador</option>
                                    <option value="Usuario">Usuario</option>
                                </select>
                                <span class="campo-error" id="agr-error-rol"></span>
                            </div>

                            <!-- Contraseña -->
                            <div class="form-grupo">
                                <label for="agr-contrasena" class="form-label">Contraseña</label>
                                <input type="password" id="agr-contrasena" name="contraseña" class="form-control"
                                       placeholder="**********" required>
                                <div class="fortaleza-barra" id="agr-fortaleza-barra"></div>
                                <div class="fortaleza-texto" id="agr-fortaleza-texto"></div>
                                <span class="campo-error" id="agr-error-contrasena"></span>
                            </div>

                            <!-- Confirmar Contraseña -->
                            <div class="form-grupo">
                                <label for="agr-confirmar" class="form-label">Confirmar Contraseña</label>
                                <input type="password" id="agr-confirmar" name="confirmarContraseña" class="form-control"
                                       placeholder="**********" required>
                                <span class="campo-error" id="agr-error-confirmar"></span>
                            </div>

                        </div>

                        <!-- Botones -->
                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Agregar Usuario</button>
                        </div>

                    </form>

                </section>
            </div>


            <!-- ============================================
                           EDITAR USUARIO
            ============================================ -->
            <div class="pestaña-contenido" id="contenido-editarUsuario">

                <!-- Buscador -->
                <section class="form-grupo">
                    <label for="nombre-usuario-editar" class="form-label">Nombre del usuario</label>
                    <div class="buscador-boton">
                        <input type="text" id="nombre-usuario-editar" name="nombre-usuario-editar"
                               class="form-control" placeholder="Ej: Juan Ramos" required>
                        <button class="btn btn-primario">Buscar</button>
                    </div>
                </section>

                <!-- TABLA de resultados -->
                <section class="form-grupo">
                    <label class="form-label">Seleccione usuario</label>
                    <table class="tabla">
                        <thead class="header-tabla">
                            <tr>
                                <th class="header-tabla">ID</th>
                                <th class="header-tabla">Nombre</th>
                                <th class="header-tabla">Correo</th>
                                <th class="header-tabla">Fecha Registro</th>
                                <th class="header-tabla">Rol</th>
                                <th class="header-tabla">Estado</th>
                                <th class="header-tabla">Acciones</th>
                            </tr>
                        </thead>
                        <tbody class="body-tabla">
                            <tr>
                                <td class="body-tabla">1</td>
                                <td class="body-tabla">Juan Ramos</td>
                                <td class="body-tabla">juan@gmail.com</td>
                                <td class="body-tabla">14-04-2026</td>
                                <td class="body-tabla"><span class="rol admin">Admin</span></td>
                                <td class="body-tabla"><span class="estado activo">Activo</span></td>
                                <td class="body-tabla"><a class="btn btn-secundario btn-elegir">Elegir</a></td>
                            </tr>
                            <tr>
                                <td class="body-tabla">-</td><td class="body-tabla">-</td>
                                <td class="body-tabla">-</td><td class="body-tabla">-</td>
                                <td class="body-tabla">-</td><td class="body-tabla">-</td>
                                <td class="body-tabla">-</td>
                            </tr>
                            <tr>
                                <td class="body-tabla">-</td><td class="body-tabla">-</td>
                                <td class="body-tabla">-</td><td class="body-tabla">-</td>
                                <td class="body-tabla">-</td><td class="body-tabla">-</td>
                                <td class="body-tabla">-</td>
                            </tr>
                        </tbody>
                    </table>
                </section>

                <!-- FORMULARIO EDITAR -->
                <section class="form-grupo">
                    <form id="form-editar-usuario" action="/gestion/adminUsuarios/actualizar" method="POST" novalidate>
                        <div class="formulario">

                            <!-- Nombres -->
                            <div class="form-grupo">
                                <label for="edi-nombres" class="form-label">Nombres</label>
                                <input type="text" id="edi-nombres" name="nombres" class="form-control"
                                       placeholder="Ej: Juan Eduardo" required>
                                <span class="campo-error" id="edi-error-nombres"></span>
                            </div>

                            <!-- Apellidos -->
                            <div class="form-grupo">
                                <label for="edi-apellidos" class="form-label">Apellidos</label>
                                <input type="text" id="edi-apellidos" name="apellidos" class="form-control"
                                       placeholder="Ej: Ramos Pérez" required>
                                <span class="campo-error" id="edi-error-apellidos"></span>
                            </div>

                            <!-- Correo -->
                            <div class="form-grupo">
                                <label for="edi-correo" class="form-label">Correo electrónico</label>
                                <input type="email" id="edi-correo" name="correo" class="form-control"
                                       placeholder="Ej: juan@email.com" required>
                                <span class="campo-error" id="edi-error-correo"></span>
                            </div>

                            <!-- Rol -->
                            <div class="form-grupo">
                                <label for="edi-rol" class="form-label">Rol</label>
                                <select id="edi-rol" name="rol" class="form-control" required>
                                    <option value="" disabled selected>Seleccione un rol</option>
                                    <option value="Admin">Administrador</option>
                                    <option value="Usuario">Usuario</option>
                                </select>
                                <span class="campo-error" id="edi-error-rol"></span>
                            </div>

                            <!-- Contraseña (opcional en edición) -->
                            <div class="form-grupo">
                                <label for="edi-contrasena" class="form-label">Contraseña <small>(dejar vacío para no cambiar)</small></label>
                                <input type="password" id="edi-contrasena" name="contraseña" class="form-control"
                                       placeholder="**********">
                                <div class="fortaleza-barra" id="edi-fortaleza-barra"></div>
                                <div class="fortaleza-texto" id="edi-fortaleza-texto"></div>
                                <span class="campo-error" id="edi-error-contrasena"></span>
                            </div>

                            <!-- Confirmar Contraseña -->
                            <div class="form-grupo">
                                <label for="edi-confirmar" class="form-label">Confirmar Contraseña</label>
                                <input type="password" id="edi-confirmar" name="confirmarContraseña" class="form-control"
                                       placeholder="**********">
                                <span class="campo-error" id="edi-error-confirmar"></span>
                            </div>

                        </div>

                        <!-- Botones -->
                        <div class="formulario-acciones">
                            <button type="reset" class="btn btn-secundario">Limpiar</button>
                            <button type="submit" class="btn btn-primario">Guardar Cambios</button>
                        </div>

                    </form>
                </section>

            </div>

        </div>

    </main>

    <!-- ===== PIE DE PÁGINA ===== -->
    <%@ include file="../footer.jsp" %>


    <!-- ============================================================
         VALIDACIÓN DE FORMULARIOS - ADMINISTRAR USUARIOS
    ============================================================ -->
    <script>
    (function () {
        "use strict";

        /* ──────────────────────────────────────────
           EXPRESIONES REGULARES Y CONSTANTES
        ────────────────────────────────────────── */
        var SOLO_LETRAS  = /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s'\-]{2,50}$/;
        var EMAIL_REGEX  = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
        var PASS_MIN     = 8;
        var PASS_NUMERO  = /[0-9]/;
        var PASS_LETRA   = /[a-zA-Z]/;
        var PASS_ESPECIAL= /[!@#$%^&*(),.?":{}|<>]/;

        /* ──────────────────────────────────────────
           HELPERS GENÉRICOS
        ────────────────────────────────────────── */
        function mostrarError(inputEl, spanId, mensaje) {
            inputEl.classList.add("error");
            inputEl.classList.remove("success");
            var span = document.getElementById(spanId);
            if (span) { span.textContent = mensaje; span.classList.add("visible"); }
        }

        function limpiarError(inputEl, spanId) {
            inputEl.classList.remove("error");
            inputEl.classList.add("success");
            var span = document.getElementById(spanId);
            if (span) { span.textContent = ""; span.classList.remove("visible"); }
        }

        function limpiarEstado(inputEl, spanId) {
            inputEl.classList.remove("error", "success");
            var span = document.getElementById(spanId);
            if (span) { span.textContent = ""; span.classList.remove("visible"); }
        }

        /* ──────────────────────────────────────────
           FORTALEZA DE CONTRASEÑA
        ────────────────────────────────────────── */
        function evaluarFortaleza(pass, barraId, textoId) {
            var barra = document.getElementById(barraId);
            var texto = document.getElementById(textoId);
            if (!barra || !texto) return;

            if (!pass) { barra.style.width = "0%"; texto.textContent = ""; return; }

            var puntos = 0;
            if (pass.length >= PASS_MIN)     puntos++;
            if (pass.length >= 12)           puntos++;
            if (PASS_NUMERO.test(pass))      puntos++;
            if (PASS_LETRA.test(pass))       puntos++;
            if (PASS_ESPECIAL.test(pass))    puntos++;

            var niveles = [
                { min: 1, color: "#e53e3e", label: "Muy débil",  pct: "20%" },
                { min: 2, color: "#dd6b20", label: "Débil",      pct: "40%" },
                { min: 3, color: "#d69e2e", label: "Regular",    pct: "60%" },
                { min: 4, color: "#38a169", label: "Fuerte",     pct: "80%" },
                { min: 5, color: "#2f855a", label: "Muy fuerte", pct: "100%" },
            ];

            var nivel = niveles[0];
            for (var i = niveles.length - 1; i >= 0; i--) {
                if (puntos >= niveles[i].min) { nivel = niveles[i]; break; }
            }

            barra.style.width           = nivel.pct;
            barra.style.backgroundColor = nivel.color;
            texto.textContent           = "Fortaleza: " + nivel.label;
            texto.style.color           = nivel.color;
        }

        /* ──────────────────────────────────────────
           FÁBRICAS DE VALIDADORES POR CAMPO
        ────────────────────────────────────────── */
        function validarNombres(inputId, spanId) {
            var v = document.getElementById(inputId).value.trim();
            var el = document.getElementById(inputId);
            if (!v)                   { mostrarError(el, spanId, "El nombre es obligatorio."); return false; }
            if (!SOLO_LETRAS.test(v)) { mostrarError(el, spanId, "Solo letras y espacios (2–50 caracteres)."); return false; }
            limpiarError(el, spanId);
            return true;
        }

        function validarApellidos(inputId, spanId) {
            var v = document.getElementById(inputId).value.trim();
            var el = document.getElementById(inputId);
            if (!v)                   { mostrarError(el, spanId, "El apellido es obligatorio."); return false; }
            if (!SOLO_LETRAS.test(v)) { mostrarError(el, spanId, "Solo letras y espacios (2–50 caracteres)."); return false; }
            limpiarError(el, spanId);
            return true;
        }

        function validarCorreo(inputId, spanId) {
            var v = document.getElementById(inputId).value.trim();
            var el = document.getElementById(inputId);
            if (!v)                   { mostrarError(el, spanId, "El correo es obligatorio."); return false; }
            if (!EMAIL_REGEX.test(v)) { mostrarError(el, spanId, "Ingresa un correo electrónico válido."); return false; }
            limpiarError(el, spanId);
            return true;
        }

        function validarRol(selectId, spanId) {
            var v = document.getElementById(selectId).value;
            var el = document.getElementById(selectId);
            if (!v) { mostrarError(el, spanId, "Selecciona un rol."); return false; }
            limpiarError(el, spanId);
            return true;
        }

        function validarContrasena(inputId, spanId, esRequerida) {
            var v = document.getElementById(inputId).value;
            var el = document.getElementById(inputId);
            if (!v && esRequerida) { mostrarError(el, spanId, "La contraseña es obligatoria."); return false; }
            if (!v && !esRequerida) { limpiarEstado(el, spanId); return true; } // Vacío en edición = sin cambio
            if (v.length < PASS_MIN) { mostrarError(el, spanId, "Mínimo " + PASS_MIN + " caracteres."); return false; }
            if (!PASS_LETRA.test(v)) { mostrarError(el, spanId, "Debe contener al menos una letra."); return false; }
            if (!PASS_NUMERO.test(v)) { mostrarError(el, spanId, "Debe contener al menos un número."); return false; }
            limpiarError(el, spanId);
            return true;
        }

        function validarConfirmar(contrasenaId, confirmarId, spanId, esRequerida) {
            var pass    = document.getElementById(contrasenaId).value;
            var confirm = document.getElementById(confirmarId).value;
            var el      = document.getElementById(confirmarId);
            if (!confirm && esRequerida) { mostrarError(el, spanId, "Confirma la contraseña."); return false; }
            if (!confirm && !esRequerida && !pass) { limpiarEstado(el, spanId); return true; }
            if (pass !== confirm) { mostrarError(el, spanId, "Las contraseñas no coinciden."); return false; }
            limpiarError(el, spanId);
            return true;
        }

        /* ──────────────────────────────────────────
           FUNCIÓN: Vincular limpieza de error al escribir
        ────────────────────────────────────────── */
        function alEscribir(inputId, spanId) {
            var el = document.getElementById(inputId);
            if (!el) return;
            el.addEventListener("input", function () {
                if (el.classList.contains("error")) {
                    el.classList.remove("error");
                    var span = document.getElementById(spanId);
                    if (span) span.classList.remove("visible");
                }
            });
        }

        /* ══════════════════════════════════════════
           FORM: AGREGAR USUARIO
        ══════════════════════════════════════════ */
        var formAgregar = document.getElementById("form-agregar-usuario");
        if (formAgregar) {

            // Blur por campo
            document.getElementById("agr-nombres").addEventListener("blur",    function(){ validarNombres("agr-nombres","agr-error-nombres"); });
            document.getElementById("agr-apellidos").addEventListener("blur",  function(){ validarApellidos("agr-apellidos","agr-error-apellidos"); });
            document.getElementById("agr-correo").addEventListener("blur",     function(){ validarCorreo("agr-correo","agr-error-correo"); });
            document.getElementById("agr-rol").addEventListener("change",      function(){ validarRol("agr-rol","agr-error-rol"); });
            document.getElementById("agr-contrasena").addEventListener("blur", function(){ validarContrasena("agr-contrasena","agr-error-contrasena",true); });
            document.getElementById("agr-confirmar").addEventListener("blur",  function(){ validarConfirmar("agr-contrasena","agr-confirmar","agr-error-confirmar",true); });

            // Fortaleza en tiempo real
            document.getElementById("agr-contrasena").addEventListener("input", function(){
                evaluarFortaleza(this.value, "agr-fortaleza-barra", "agr-fortaleza-texto");
            });

            // Limpiar borde rojo al escribir
            alEscribir("agr-nombres",    "agr-error-nombres");
            alEscribir("agr-apellidos",  "agr-error-apellidos");
            alEscribir("agr-correo",     "agr-error-correo");
            alEscribir("agr-contrasena", "agr-error-contrasena");
            alEscribir("agr-confirmar",  "agr-error-confirmar");

            // Submit
            formAgregar.addEventListener("submit", function(e) {
                e.preventDefault();
                var ok = [
                    validarNombres("agr-nombres","agr-error-nombres"),
                    validarApellidos("agr-apellidos","agr-error-apellidos"),
                    validarCorreo("agr-correo","agr-error-correo"),
                    validarRol("agr-rol","agr-error-rol"),
                    validarContrasena("agr-contrasena","agr-error-contrasena",true),
                    validarConfirmar("agr-contrasena","agr-confirmar","agr-error-confirmar",true),
                ].every(Boolean);

                if (ok) {
                    formAgregar.submit();
                } else {
                    var primerError = formAgregar.querySelector(".form-control.error");
                    if (primerError) primerError.focus();
                }
            });
        }

        /* ══════════════════════════════════════════
           FORM: EDITAR USUARIO
        ══════════════════════════════════════════ */
        var formEditar = document.getElementById("form-editar-usuario");
        if (formEditar) {

            // Blur por campo
            document.getElementById("edi-nombres").addEventListener("blur",    function(){ validarNombres("edi-nombres","edi-error-nombres"); });
            document.getElementById("edi-apellidos").addEventListener("blur",  function(){ validarApellidos("edi-apellidos","edi-error-apellidos"); });
            document.getElementById("edi-correo").addEventListener("blur",     function(){ validarCorreo("edi-correo","edi-error-correo"); });
            document.getElementById("edi-rol").addEventListener("change",      function(){ validarRol("edi-rol","edi-error-rol"); });
            document.getElementById("edi-contrasena").addEventListener("blur", function(){ validarContrasena("edi-contrasena","edi-error-contrasena",false); });
            document.getElementById("edi-confirmar").addEventListener("blur",  function(){ validarConfirmar("edi-contrasena","edi-confirmar","edi-error-confirmar",false); });

            // Fortaleza en tiempo real
            document.getElementById("edi-contrasena").addEventListener("input", function(){
                evaluarFortaleza(this.value, "edi-fortaleza-barra", "edi-fortaleza-texto");
            });

            // Limpiar borde rojo al escribir
            alEscribir("edi-nombres",    "edi-error-nombres");
            alEscribir("edi-apellidos",  "edi-error-apellidos");
            alEscribir("edi-correo",     "edi-error-correo");
            alEscribir("edi-contrasena", "edi-error-contrasena");
            alEscribir("edi-confirmar",  "edi-error-confirmar");

            // Submit
            formEditar.addEventListener("submit", function(e) {
                e.preventDefault();
                var ok = [
                    validarNombres("edi-nombres","edi-error-nombres"),
                    validarApellidos("edi-apellidos","edi-error-apellidos"),
                    validarCorreo("edi-correo","edi-error-correo"),
                    validarRol("edi-rol","edi-error-rol"),
                    validarContrasena("edi-contrasena","edi-error-contrasena",false),
                    validarConfirmar("edi-contrasena","edi-confirmar","edi-error-confirmar",false),
                ].every(Boolean);

                if (ok) {
                    formEditar.submit();
                } else {
                    var primerError = formEditar.querySelector(".form-control.error");
                    if (primerError) primerError.focus();
                }
            });
        }

    })();
    </script>

</body>
</html>
