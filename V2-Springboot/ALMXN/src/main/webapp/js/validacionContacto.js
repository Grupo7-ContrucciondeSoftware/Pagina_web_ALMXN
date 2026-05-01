document.addEventListener('DOMContentLoaded',function () {
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

});