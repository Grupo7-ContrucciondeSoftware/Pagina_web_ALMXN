<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<link rel="stylesheet" href="/css/restriccion.css">
<script src="/js/restriccion.js" defer></script>

<div id="modal-sin-permiso" class="modal-oculto">
    <div class="modal-contenedor-restriccion">
        <div class="modal-header-restriccion">
            <h2 style="color: red; margin: 0;">Acceso Denegado</h2>
            <button class="btn-cerrar-modal" onclick="cerrarModalPermiso()">&times;</button>
        </div>
        <div class="modal-body-restriccion">
            <p style="margin-bottom: 10px;">Lo sentimos, tu rol actual de <strong>${sessionScope.usuarioLogueado.rol}</strong> no tiene los permisos necesarios para realizar esta acción.</p>
            <p>Por favor, contacta a un Administrador si necesitas realizar modificaciones.</p>
        </div>
        <div class="modal-acciones-restriccion">
            <button class="btn btn-secundario" onclick="cerrarModalPermiso()">Entendido</button>
        </div>
    </div>
</div>