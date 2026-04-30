<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Contacto - Sistema de Gestión de Almacén">
    <title>ALMXN - Contacto</title>

    <!-- ===== ESTILOS ===== -->
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/contacto.css">

    <script src="/js/tema.js" defer></script>

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
    </style>

</head>

<body>

    <%@ include file="header.jsp" %>

    <!-- ===== CONTENIDO PRINCIPAL ===== -->
    <main id="contacto-principal">

        <!-- TITULO/INICIO -->
        <div class="contacto-header">
            <h1 class="titulo-seccion">Soporte y Contacto</h1>
            <p class="subtitulo-seccion">¿Tienes algún problema con el sistema o necesitas asistencia? Escríbenos.</p>
        </div>

        <!-- CUERPO -->
        <article class="contacto-modulo">
            <div class="contacto-imagen">
                <img src="https://images.unsplash.com/photo-1521791055366-0d553872125f?auto=format&fit=crop&q=80&w=600"
                    alt="Equipo de soporte">
            </div>

            <div class="contacto-contenido">

                <!-- FORMULARIO -->
                <form id="formulario-contacto" action="main.html" class="formulario" novalidate>
                    <h2 class="titulo-seccion">Rellenar información</h2>

                    <div class="form-fila">
                        <div class="form-grupo">
                            <label for="nombres" class="form-label">Nombres</label>
                            <input type="text" id="nombres" name="nombres" class="form-control"
                                   placeholder="Ej: Juan Carlos" required>
                            <span class="campo-error" id="error-nombres"></span>
                        </div>
                        <div class="form-grupo">
                            <label for="apellidos" class="form-label">Apellidos</label>
                            <input type="text" id="apellidos" name="apellidos" class="form-control"
                                   placeholder="Ej: Pérez García" required>
                            <span class="campo-error" id="error-apellidos"></span>
                        </div>
                    </div>

                    <div class="form-fila">
                        <div class="form-grupo">
                            <label for="correo" class="form-label">Correo</label>
                            <input type="email" id="correo" name="correo" class="form-control"
                                   placeholder="ejemplo@correo.com" required>
                            <span class="campo-error" id="error-correo"></span>
                        </div>
                        <div class="form-grupo">
                            <label for="celular" class="form-label">Celular</label>
                            <input type="tel" id="celular" name="celular" class="form-control"
                                   placeholder="Ej: 987654321" required>
                            <span class="campo-error" id="error-celular"></span>
                        </div>
                    </div>

                    <div class="form-grupo">
                        <label for="motivo" class="form-label">Motivo de Contacto (Opcional)</label>
                        <textarea id="motivo" name="motivo" class="form-control" rows="3"
                            placeholder="Describe tu consulta..."></textarea>
                        <span class="campo-error" id="error-motivo"></span>
                    </div>

                    <!-- BOTON SUBMIT -->
                    <button type="submit" class="btn btn-primario">Enviar Mensaje</button>
                </form>

                <!-- MEDIOS DE CONTACTO -->
                <div class="contacto-medios">
                    <p class="subtitulo-seccion">O puedes contactarte por estos medios:</p>
                    <div class="medios-links">
                        <a href="" class="medio-item">+51 966595731</a>
                        <a href="" class="medio-item">alonsocsr2305@gmail.com</a>
                    </div>
                </div>

            </div>
        </article>
    </main>

    <!-- ===== PIE DE PÁGINA ===== -->
    <%@ include file="footer.jsp" %>

    <!-- ===== VALIDACIÓN DEL FORMULARIO ===== -->
    <script>
        (function () {
            "use strict";

            /* ---------- Reglas de validación ---------- */
            const SOLO_LETRAS   = /^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\s'-]{2,50}$/;
            const EMAIL_REGEX   = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
            const CELULAR_REGEX = /^[\d\s\+\-\(\)]{7,15}$/;
            const MAX_MOTIVO    = 500;

            /* ---------- Referencias a elementos ---------- */
            const form  = document.getElementById("formulario-contacto");
            const campos = {
                nombres:   document.getElementById("nombres"),
                apellidos: document.getElementById("apellidos"),
                correo:    document.getElementById("correo"),
                celular:   document.getElementById("celular"),
                motivo:    document.getElementById("motivo"),
            };

            /* ---------- Helpers ---------- */
            function mostrarError(id, mensaje) {
                const campo = campos[id];
                const span  = document.getElementById("error-" + id);
                campo.classList.add("error");
                campo.classList.remove("success");
                span.textContent = mensaje;
                span.classList.add("visible");
            }

            function limpiarError(id) {
                const campo = campos[id];
                const span  = document.getElementById("error-" + id);
                campo.classList.remove("error");
                campo.classList.add("success");
                span.textContent = "";
                span.classList.remove("visible");
            }

            /* ---------- Validación por campo ---------- */
            function validarNombres() {
                const v = campos.nombres.value.trim();
                if (!v)                   { mostrarError("nombres", "El nombre es obligatorio."); return false; }
                if (!SOLO_LETRAS.test(v)) { mostrarError("nombres", "Solo letras y espacios (2–50 caracteres)."); return false; }
                limpiarError("nombres");
                return true;
            }

            function validarApellidos() {
                const v = campos.apellidos.value.trim();
                if (!v)                   { mostrarError("apellidos", "El apellido es obligatorio."); return false; }
                if (!SOLO_LETRAS.test(v)) { mostrarError("apellidos", "Solo letras y espacios (2–50 caracteres)."); return false; }
                limpiarError("apellidos");
                return true;
            }

            function validarCorreo() {
                const v = campos.correo.value.trim();
                if (!v)                  { mostrarError("correo", "El correo es obligatorio."); return false; }
                if (!EMAIL_REGEX.test(v)){ mostrarError("correo", "Ingresa un correo electrónico válido."); return false; }
                limpiarError("correo");
                return true;
            }

            function validarCelular() {
                const v = campos.celular.value.trim();
                if (!v)                    { mostrarError("celular", "El celular es obligatorio."); return false; }
                if (!CELULAR_REGEX.test(v)){ mostrarError("celular", "Ingresa un número válido (7–15 dígitos)."); return false; }
                limpiarError("celular");
                return true;
            }

            function validarMotivo() {
                const v = campos.motivo.value.trim();
                if (v.length > MAX_MOTIVO) {
                    mostrarError("motivo", "El motivo no puede superar los " + MAX_MOTIVO + " caracteres.");
                    return false;
                }
                campos.motivo.classList.remove("error", "success");
                document.getElementById("error-motivo").classList.remove("visible");
                return true;
            }

            /* ---------- Validación en tiempo real al salir del campo (blur) ---------- */
            campos.nombres.addEventListener("blur",   validarNombres);
            campos.apellidos.addEventListener("blur", validarApellidos);
            campos.correo.addEventListener("blur",    validarCorreo);
            campos.celular.addEventListener("blur",   validarCelular);
            campos.motivo.addEventListener("input",   validarMotivo);

            /* ---------- Limpiar borde de error mientras escribe ---------- */
            ["nombres", "apellidos", "correo", "celular"].forEach(function (id) {
                campos[id].addEventListener("input", function () {
                    if (campos[id].classList.contains("error")) {
                        campos[id].classList.remove("error");
                        document.getElementById("error-" + id).classList.remove("visible");
                    }
                });
            });

            /* ---------- Validación total al enviar ---------- */
            form.addEventListener("submit", function (e) {
                e.preventDefault();

                const ok = [
                    validarNombres(),
                    validarApellidos(),
                    validarCorreo(),
                    validarCelular(),
                    validarMotivo(),
                ].every(Boolean);

                if (ok) {
                    form.submit();
                } else {
                    const primerError = form.querySelector(".form-control.error");
                    if (primerError) primerError.focus();
                }
            });

        })();
    </script>

</body>

</html>
