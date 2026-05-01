package Project.ALMXN.Repository;

import java.util.List;

import Project.ALMXN.models.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UsuarioRepository implements UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> UsuarioRowMapper = (rs,rowNum) -> {
        return new Usuario(
            rs.getInt("id_usuario"),
            rs.getString("nombres"),
            rs.getString("apellidos"),
            rs.getString("correo"),
            rs.getDate("fechaCreacion").toLocalDate(),
            rs.getString("contraseña"),
            rs.getString("rol"),
            rs.getString("estado")
        );
    };

    @Override
    public List<Usuario> listarTodos(){
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, UsuarioRowMapper);
    }

    @Override
    public void guardarUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario (nombres, apellidos, correo, contraseña, rol) "+
                "VALUES ( ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getContraseña(),
                usuario.getRol()
        );
    }

    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        return jdbcTemplate.queryForObject(sql, UsuarioRowMapper, idUsuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nombres = ?, apellidos = ?, correo = ?, " +
                "rol = ?, estado = ? WHERE id_usuario = ?";

        jdbcTemplate.update(sql,
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getRol(),
                usuario.getEstado(),
                usuario.getIdUsuario() // El ID para el WHERE
        );
    }

}
