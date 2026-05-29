package Project.ALMXN.Repository;

import java.util.List;

import Project.ALMXN.entitys.DetalleMovimientoEntity;
import Project.ALMXN.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimientoEntity, Long> {
/*
    private final JdbcTemplate jdbcTemplate;

    public DetalleMovimientoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<DetalleMovimiento> DetalleRowMapper = (rs, rowNum) -> {
        Producto prod = new Producto();
        prod.setIdProducto(rs.getLong("id_producto"));
        prod.setCodigoProducto(rs.getString("codigo_producto"));
        prod.setNombreProducto(rs.getString("nombre_producto"));

        Movimiento mov = new Movimiento();
        mov.setIdMovimiento(rs.getInt("id_movimiento"));

        DetalleMovimiento detalle = new DetalleMovimiento();
        detalle.setIdDetalleMovimiento(rs.getInt("id_detalle"));
        detalle.setCantidadDetalleMovimiento(rs.getInt("cantidad"));
        detalle.setPrecioUnitarioDetalleMovimiento(rs.getDouble("precio_unitario"));
        detalle.setSubtotalDetalleMovimiento(rs.getDouble("subtotal"));

        detalle.setProducto(prod);
        detalle.setMovimiento(mov);

        return detalle;
    };

    @Override
    public List<DetalleMovimiento> buscarPorIdMovimiento(int idMovimiento) {
        String sql = "SELECT dm.*, p.codigo AS codigo_producto, p.nombre AS nombre_producto " +
                "FROM detalle_movimiento dm " +
                "INNER JOIN producto p ON dm.id_producto = p.id_producto " +
                "WHERE dm.id_movimiento = ?";

        return jdbcTemplate.query(sql, DetalleRowMapper, idMovimiento);
    }

    @Override
    public void guardarDetalleMovimiento(DetalleMovimiento detalleMovimiento) {
        String sql = "INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                detalleMovimiento.getMovimiento().getIdMovimiento(),
                detalleMovimiento.getProducto().getIdProducto(),
                detalleMovimiento.getCantidadDetalleMovimiento(),
                detalleMovimiento.getPrecioUnitarioDetalleMovimiento(),
                detalleMovimiento.getSubtotalDetalleMovimiento()
        );
    }

*/
}