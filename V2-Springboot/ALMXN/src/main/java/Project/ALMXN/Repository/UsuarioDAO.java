package Project.ALMXN.Repository;

import java.util.List;
import Project.ALMXN.models.Usuario;

public interface UsuarioDAO {

    List<Usuario> listarTodos();

    void guardarUsuario(Usuario usuario);

    Usuario buscarPorCorreoYContrasena(String correo, String contraseña);
}