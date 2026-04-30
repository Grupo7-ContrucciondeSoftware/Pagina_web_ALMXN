package Project.ALMXN.Repository;

import java.util.List;

import Project.ALMXN.models.Usuario;

public interface UsuarioDAO {

    List<Usuario> listarTodos();

    public void guardarUsuario(Usuario Usuario);
}
