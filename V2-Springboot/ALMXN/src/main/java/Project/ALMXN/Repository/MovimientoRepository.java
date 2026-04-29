package Project.ALMXN.Repository;

import java.util.List;

import Project.ALMXN.models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MovimientoRepository implements MovimientoDAO{

    public final JdbcTemplate jdbcTemplate;

    public MovimientoRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private final RowMapper<Movimiento> MovimientoRowMapper = (rs, rowNum) -> {
        return new Movimiento(
                rs.getInt("id_movimiento"),
                rs.getString("tipo_movimiento"),
                rs.getDate("fecha_movimiento").toLocalDate(),
                rs.getString("motivo"),
                rs.getString("destino"),
                rs.getString("observaciones"),
                new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("correo_usuario"),
                        rs.getDate("fechaCreacion").toLocalDate(),
                        rs.getString("contraseña"),
                        rs.getString("rol"),
                        rs.getString("estado")
                ),
                new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("ruc"),
                        rs.getString("razon_social"),
                        rs.getString("telefono"),
                        rs.getString("correo_proveedor")
                ),
                rs.getDouble("total_movimiento")
        );
    };

    @Override
    public List<Movimiento> listaMovimientos(){
        String sql = "SELECT " +
                "m.*, " +
                "u.id_usuario, u.nombres, u.apellidos, u.correo AS correo_usuario, " +
                "u.fechaCreacion, u.contraseña, u.rol, u.estado, " +
                "p.id_proveedor, p.ruc, p.razon_social, p.telefono, p.correo AS correo_proveedor " +
                "FROM movimiento m " +
                "INNER JOIN usuario u ON m.id_usuario = u.id_usuario " +
                "LEFT JOIN proveedor p ON m.id_proveedor = p.id_proveedor";
        return jdbcTemplate.query(sql, MovimientoRowMapper);
    }

}
