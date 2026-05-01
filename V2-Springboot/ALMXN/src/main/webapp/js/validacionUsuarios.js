document.addEventListener("DOMContentLoaded", function () {
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

})