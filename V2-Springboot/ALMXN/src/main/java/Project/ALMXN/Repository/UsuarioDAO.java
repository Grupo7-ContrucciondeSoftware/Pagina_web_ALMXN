package Project.ALMXN.Repository;

import java.util.List;

import Project.ALMXN.models.Usuario;

public interface UsuarioDAO {

    List<Usuario> listarTodos();

    public void guardarUsuario(Usuario usuario);

    public Usuario buscarUsuarioPorId(int idUsuario);

    public void actualizarUsuario(Usuario usuario);
}
