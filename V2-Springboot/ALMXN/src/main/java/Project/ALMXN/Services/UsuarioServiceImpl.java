package Project.ALMXN.Services;

import Project.ALMXN.Repository.UsuarioDAO;
import Project.ALMXN.models.Usuario;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.listarTodos();
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.guardarUsuario(usuario);
    }

    @Override
    public Usuario validarLogin(String correo, String contraseña) {
        return usuarioDAO.buscarPorCorreoYContrasena(correo, contraseña);
    }
}