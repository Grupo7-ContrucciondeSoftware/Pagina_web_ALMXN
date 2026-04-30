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

    private final RowMapper<Usuario> UsuarioRowMapper = (rs, rowNum) -> {
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
    public List<Usuario> listarTodos() {
        String query = "SELECT * FROM usuario";
        return jdbcTemplate.query(query, UsuarioRowMapper);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuario (nombres, apellidos, correo, contraseña, rol) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getContraseña(),
                usuario.getRol()
        );
    }

    @Override
    public Usuario buscarPorCorreoYContrasena(String correo, String contraseña) {
        String query = "SELECT * FROM usuario WHERE correo = ? AND contraseña = ?";
        List<Usuario> resultado = jdbcTemplate.query(query, UsuarioRowMapper, correo, contraseña);
        return resultado.isEmpty() ? null : resultado.get(0);
    }
}