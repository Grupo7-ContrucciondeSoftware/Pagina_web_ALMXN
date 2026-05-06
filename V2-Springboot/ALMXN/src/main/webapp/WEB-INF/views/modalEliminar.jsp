<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<div id="modal-confirmar-eliminar" class="modal-oculto">
    <div class="modal-body">
        <h2>¿Confirmar Eliminación?</h2>
        <p class="modal-info">
            ¿Está seguro que desea eliminar: <br>
            <strong id="texto-nombre-eliminar" style="color: var(--texto-principal);">---</strong>?
        </p>

        <form id="form-eliminar-dinamico" method="POST" style="display: flex; justify-content: center; gap: 1rem;">
            <input type="hidden" id="input-id-eliminar">

            <button type="button" class="btn btn-secundario" id="btn-cancelar-eliminar">Cancelar</button>
            <button type="submit" class="btn btn-primario btn-eliminar-modal">Sí, Eliminar</button>
        </form>
    </div>
</div>