package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Usuario;

public interface UsuarioService {

    List<Usuario> obtenerTodosLosUsuarios();

    void guardarUsuario(Usuario usuario);

    Usuario buscarUsuarioPorId(int idUsuario);

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(int idUsuario);

    void activarUsuario(int idUsuario);

    Usuario validarLogin(String correo, String contraseña);

    List<Usuario> filtrarUsuario(String nombres, String rol, String estado, String fechaMin, String fechaMax);
}