package Project.ALMXN.Repository;

import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Movimiento;
import Project.ALMXN.models.Producto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    @Override
    public Producto buscarProductoPorId(int idProducto) {
        String sql = "SELECT p.*, c.nombre AS categoria_nombre, c.descripcion AS categoria_descripcion " +
                "FROM producto p " +
                "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                "WHERE p.id_producto = ?";

        return jdbcTemplate.queryForObject(sql, ProductoRowMapper, idProducto);
    }

    @Override
    public void actualizarProducto (Producto producto){
        String sql = "UPDATE producto SET codigo = ?, nombre = ?, id_categoria = ?, " +
                "stock_actual = ?, unidad_medida = ?, stock_minimo = ?, " +
                "precio_costo = ?, precio_venta = ?, descripcion = ? " +
                "WHERE id_producto = ?";

        jdbcTemplate.update(sql,
                producto.getCodigoProducto(),
                producto.getNombreProducto(),
                producto.getCategoria().getIdCategoria(),
                producto.getStockActualProducto(),
                producto.getUnidadMedidaProducto(),
                producto.getStockMinimoProducto(),
                producto.getPrecioCostoProducto(),
                producto.getPrecioVentaProducto(),
                producto.getDescripcionProducto(),
                producto.getIdProducto()
        );
    }

    @Override
    public void eliminarProducto(int idProducto){
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        jdbcTemplate.update(sql, idProducto);
    }

    @Override
    public List<Producto> buscarProductosParaMovimiento(String filtro) {
        String sql = "SELECT p.*, c.nombre AS categoria_nombre, c.descripcion AS categoria_descripcion " +
                "FROM producto p " +
                "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                "WHERE p.codigo LIKE ? OR p.nombre LIKE ?";
        String parametro = "%" + filtro + "%";
        return jdbcTemplate.query(sql, ProductoRowMapper, parametro, parametro);
    }

    @Override
    public void actualizarStock(int idProducto, int cantidadAjuste) {
        String sql = "UPDATE producto SET stock_actual = stock_actual + ? WHERE id_producto = ?";

        jdbcTemplate.update(sql, cantidadAjuste, idProducto);
    }

    @Override
    public List<Producto> filtrarProducto(String nombre, Integer idCategoria, Integer stockMin, Integer stockMax, Integer precioMin, Integer precioMax, String fechaMin, String fechaMax) {

        StringBuilder sql = new StringBuilder(
                "SELECT p.*, c.nombre AS categoria_nombre, c.descripcion AS categoria_descripcion " +
                        "FROM producto p " +
                        "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                        "WHERE 1=1"
        );

        List<Object> parametros = new ArrayList<>();

        if (nombre != null && !nombre.trim().isEmpty()) {
            sql.append(" AND LOWER(p.nombre) LIKE LOWER(?)");
            parametros.add("%" + nombre.trim() + "%");
        }

        if (idCategoria != null) {
            sql.append(" AND p.id_categoria = ?");
            parametros.add(idCategoria);
        }

        if (stockMin != null) {
            sql.append(" AND p.stock_actual >= ?");
            parametros.add(stockMin);
        }

        if (stockMax != null) {
            sql.append(" AND p.stock_actual <= ?");
            parametros.add(stockMax);
        }

        if (precioMin != null){
            sql.append(" AND p.precio_venta >= ?");
            parametros.add(precioMin);
        }

        if (precioMax != null){
            sql.append(" AND p.precio_venta <= ?");
            parametros.add(precioMax);
        }

        if (fechaMin != null && !fechaMin.isEmpty()) {
            sql.append(" AND p.fecha_creacion >= ?");
            parametros.add(fechaMin);
        }

        if (fechaMax != null && !fechaMax.isEmpty()) {
            sql.append(" AND p.fecha_creacion <= ?");
            parametros.add(fechaMax);
        }

        return jdbcTemplate.query(sql.toString(), ProductoRowMapper, parametros.toArray());
    }

}