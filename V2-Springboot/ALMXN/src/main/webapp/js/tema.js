// ============================================
// CAMBIAR DE MODO
// ============================================

document.addEventListener('DOMContentLoaded', function () {

    const toggle = document.getElementById('theme-toggle');
    const temaGuardado = localStorage.getItem('tema');

    if (temaGuardado === 'oscuro') {
        toggle.checked = true;
    }

    toggle.addEventListener('change', function () {
        if (this.checked) {
            localStorage.setItem('tema', 'oscuro');
        } else {
            localStorage.setItem('tema', 'claro');
        }
    });

});

document.addEventListener('DOMContentLoaded', () => {

    const parametrosURL = new URLSearchParams(window.location.search);
    const idPestaña = parametrosURL.get('tab');

    if (idPestaña) {
        const radioPestaña = document.getElementById(idPestaña);

        if (radioPestaña) {
            radioPestaña.checked = true;
        }
    }
});