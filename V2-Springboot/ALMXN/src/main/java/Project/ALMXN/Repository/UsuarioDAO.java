package Project.ALMXN.Repository;

import java.util.List;
import Project.ALMXN.models.Usuario;

public interface UsuarioDAO {

    List<Usuario> listarTodos();

    void guardarUsuario(Usuario usuario);

    Usuario buscarUsuarioPorId(int idUsuario);

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(int idUsuario);

    void activarUsuario(int idUsuario);

    Usuario buscarPorCorreoYContrasena(String correo, String contraseña);

    List<Usuario> filtrarUsuario(String nombres, String rol, String estado, String fechaMin, String fechaMax);
}