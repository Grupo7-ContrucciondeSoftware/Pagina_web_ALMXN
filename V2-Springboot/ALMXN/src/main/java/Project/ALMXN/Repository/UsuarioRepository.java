package Project.ALMXN.Repository;

import java.util.ArrayList;
import java.util.List;

import Project.ALMXN.models.Movimiento;
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
                usuario.getIdUsuario()
        );
    }

    @Override
    public Usuario buscarPorCorreoYContrasena(String correo, String contraseña) {
        String sql = "SELECT * FROM usuario WHERE correo = ? AND contraseña = ?";
        List<Usuario> resultado = jdbcTemplate.query(sql, UsuarioRowMapper, correo, contraseña);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public List<Usuario> filtrarUsuario(String nombres, String rol, String estado, String fechaMin, String fechaMax) {

        StringBuilder sql = new StringBuilder("SELECT * FROM usuario WHERE 1=1");

        List<Object> parametros = new ArrayList<>();

        if (nombres != null && !nombres.trim().isEmpty()) {
            sql.append(" AND LOWER(nombres) LIKE LOWER(?)");
            parametros.add( "%" + nombres.trim() + "%");
        }

        if (rol != null && !rol.isEmpty()) {
            sql.append(" AND rol = ?");
            parametros.add(rol);
        }

        if (estado != null && !estado.isEmpty()) {
            sql.append(" AND estado = ?");
            parametros.add(estado);
        }

        if (fechaMin != null && !fechaMin.isEmpty()) {
            sql.append(" AND fechaCreacion >= ?");
            parametros.add(fechaMin);
        }

        if (fechaMax != null && !fechaMax.isEmpty()) {
            sql.append(" AND fechaCreacion <= ?");
            parametros.add(fechaMax);
        }

        return jdbcTemplate.query(sql.toString(), UsuarioRowMapper, parametros.toArray());
    }

}