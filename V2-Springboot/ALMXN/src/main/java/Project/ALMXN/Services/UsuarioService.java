package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Usuario;

public interface UsuarioService {

    public List<Usuario> obtenerTodosLosUsuarios();

    public void guardarUsuario(Usuario Usuario);

}
