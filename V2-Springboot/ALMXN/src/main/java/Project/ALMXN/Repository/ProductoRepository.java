package Project.ALMXN.Repository;

import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Producto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductoRepository implements ProductoDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Producto> ProductoRowMapper = (rs, rowNum) -> {
        return new Producto(
                rs.getInt("id_producto"),
                rs.getString("codigo"),
                rs.getDate("fecha_creacion").toLocalDate(),
                rs.getString("nombre"),
                new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("categoria_nombre"),
                        rs.getString("categoria_descripcion")
                ),
                rs.getString("unidad_medida"),
                rs.getInt("stock_actual"),
                rs.getInt("stock_minimo"),
                rs.getDouble("precio_costo"),
                rs.getDouble("precio_venta"),
                rs.getString("descripcion")
        );
    };

    @Override
    public List<Producto> listaProductos() {
        String sql = "SELECT p.*, c.nombre AS categoria_nombre, c.descripcion AS categoria_descripcion " +
                "FROM producto p " +
                "INNER JOIN categoria c ON p.id_categoria = c.id_categoria";
        return jdbcTemplate.query(sql, ProductoRowMapper);
    }

    @Override
    public void guardarProducto(Producto producto) {
        String sql = "INSERT INTO producto (codigo, nombre, id_categoria, stock_actual, " +
                "unidad_medida, stock_minimo, precio_costo, precio_venta, descripcion) VALUES "+
                "(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        jdbcTemplate.update(sql,
                producto.getCodigoProducto(),
                producto.getNombreProducto(),
                producto.getCategoria().getIdCategoria(),
                producto.getStockActualProducto(),
                producto.getUnidadMedidaProducto(),
                producto.getStockMinimoProducto(),
                producto.getPrecioCostoProducto(),
                producto.getPrecioVentaProducto(),
                producto.getDescripcionProducto()
        );
    }

}