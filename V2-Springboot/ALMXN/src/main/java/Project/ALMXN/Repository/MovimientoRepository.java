package Project.ALMXN.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Project.ALMXN.models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class MovimientoRepository implements MovimientoDAO{

    public final JdbcTemplate jdbcTemplate;

    public MovimientoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
                        rs.getString("correo_proveedor"),
                        rs.getString("estado_proveedor")
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
                "p.id_proveedor, p.ruc, p.razon_social, p.telefono, p.correo AS correo_proveedor, p.estado AS estado_proveedor " +
                "FROM movimiento m " +
                "INNER JOIN usuario u ON m.id_usuario = u.id_usuario " +
                "LEFT JOIN proveedor p ON m.id_proveedor = p.id_proveedor";
        return jdbcTemplate.query(sql, MovimientoRowMapper);
    }

    @Override
    public int guardarMovimiento(Movimiento movimiento){
        String sql = "INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, " +
                "id_usuario, id_proveedor, total_movimiento) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?)";

        final Integer idProveedorSeguro = (movimiento.getProveedor() != null) ? movimiento.getProveedor().getIdProveedor() : null;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, movimiento.getTipoMovimiento());
            ps.setObject(2, movimiento.getFechaMovimiento());
            ps.setString(3, movimiento.getMotivoMovimiento());
            ps.setString(4, movimiento.getDestinoMovimiento());
            ps.setString(5, movimiento.getObservacionesMovimiento());
            ps.setInt(6, movimiento.getUsuario().getIdUsuario());
            ps.setObject(7, idProveedorSeguro);
            ps.setDouble(8,movimiento.getTotalMovimiento());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();

    }

    @Override
    public List<Movimiento> filtrarMovimientos(String tipo, Integer idUsuario, String fechaMin, String fechaMax) {

        StringBuilder sql = new StringBuilder(
                "SELECT " +
                        "m.*, " +
                        "u.id_usuario, u.nombres, u.apellidos, u.correo AS correo_usuario, " +
                        "u.fechaCreacion, u.contraseña, u.rol, u.estado, " +
                        "p.id_proveedor, p.ruc, p.razon_social, p.telefono, p.correo AS correo_proveedor " +
                        "FROM movimiento m " +
                        "INNER JOIN usuario u ON m.id_usuario = u.id_usuario " +
                        "LEFT JOIN proveedor p ON m.id_proveedor = p.id_proveedor " +
                        "WHERE 1=1"
        );

        List<Object> parametros = new ArrayList<>();

        if (tipo != null && !tipo.isEmpty()) {
            sql.append(" AND m.tipo_movimiento = ?");
            parametros.add(tipo);
        }

        if (idUsuario != null) {
            sql.append(" AND m.id_usuario = ?");
            parametros.add(idUsuario);
        }

        if (fechaMin != null && !fechaMin.isEmpty()) {
            sql.append(" AND m.fecha_movimiento >= ?");
            parametros.add(fechaMin);
        }

        if (fechaMax != null && !fechaMax.isEmpty()) {
            sql.append(" AND m.fecha_movimiento <= ?");
            parametros.add(fechaMax);
        }

        return jdbcTemplate.query(sql.toString(), MovimientoRowMapper, parametros.toArray());
    }

}
