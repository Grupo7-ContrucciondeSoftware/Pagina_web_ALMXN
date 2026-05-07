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
        if (usuario.getIdUsuario() == null){
            usuario.setEstadoUsuario("Activo");
            usuarioDAO.guardarUsuario(usuario);
        } else {
            usuarioDAO.actualizarUsuario(usuario);
        }
    }

    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        return usuarioDAO.buscarUsuarioPorId(idUsuario);
    }

    @Override
    public void eliminarUsuario(int idUsuario){
        usuarioDAO.eliminarUsuario(idUsuario);
    }

    @Override
    public void activarUsuario(int idUsuario){
        usuarioDAO.activarUsuario(idUsuario);
    }

    @Override
    public Usuario validarLogin(String correo, String contraseña) {
        return usuarioDAO.buscarPorCorreoYContrasena(correo, contraseña);
    }

    @Override
    public List<Usuario> filtrarUsuario(String nombres, String rol, String estado, String fechaMin, String fechaMax){
        return usuarioDAO.filtrarUsuario(nombres, rol, estado, fechaMin, fechaMax);
    }
}