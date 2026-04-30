document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector(".formulario");

    if (form) {
        form.addEventListener("submit", function (e) {
            const correo = document.getElementById("correo").value.trim();
            const contrasena = document.getElementById("contraseña").value.trim();

            if (correo === "" || contrasena === "") {
                e.preventDefault();
                alert("Por favor completa todos los campos.");
                return;
            }

            const regexCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!regexCorreo.test(correo)) {
                e.preventDefault();
                alert("Ingresa un correo con formato válido.");
                return;
            }
        });
    }
});