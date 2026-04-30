package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Usuario;

public interface UsuarioService {

    List<Usuario> obtenerTodosLosUsuarios();

    void guardarUsuario(Usuario usuario);

    Usuario validarLogin(String correo, String contraseña);
}