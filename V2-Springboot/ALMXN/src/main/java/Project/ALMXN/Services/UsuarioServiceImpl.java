package Project.ALMXN.Services;

import Project.ALMXN.Repository.UsuarioDAO;
import Project.ALMXN.Repository.UsuarioRepository;
import Project.ALMXN.models.Usuario;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioDAO UsuarioDAO;

    public UsuarioServiceImpl(UsuarioDAO UsuarioDAO) {
        this.UsuarioDAO = UsuarioDAO;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios(){
        return UsuarioDAO.listarTodos();
    }

    @Override
    public void guardarUsuario(Usuario Usuario){
        UsuarioDAO.guardarUsuario(Usuario);
    }
}