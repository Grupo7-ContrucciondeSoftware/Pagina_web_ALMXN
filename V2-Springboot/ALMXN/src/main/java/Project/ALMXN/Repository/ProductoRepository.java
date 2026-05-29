package Project.ALMXN.Repository;

import Project.ALMXN.entitys.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>, JpaSpecificationExecutor<ProductoEntity> {

    int countByCategoriaIdCategoria(Long idCategoria);

    List<ProductoEntity> findByCategoriaIdCategoria(Long idCategoria);

    List<ProductoEntity> findByCodigoContainingOrNombreContainingIgnoreCaseAndEstadoProducto(
            String codigoFiltro, String nombreFiltro, String estado);
/*
    private final JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Producto> ProductoRowMapper = (rs, rowNum) -> {
        return new Producto(
                rs.getLong("id_producto"),
                rs.getString("codigo"),
                rs.getDate("fecha_creacion").toLocalDate(),
                rs.getString("nombre"),
                new Categoria(
                        rs.getLong("id_categoria"),
                        rs.getString("categoria_nombre"),
                        rs.getString("categoria_descripcion"),
                        rs.getString("categoria_estado")),
                rs.getInt("stock_actual"),
                rs.getString("unidad_medida"),
                rs.getDouble("precio_costo"),
                rs.getDouble("precio_venta"),
                rs.getString("descripcion"),
                rs.getString("estado"));
    };

    @Override
    public List<Producto> listaProductos() {
        String sql = "SELECT p.*, c.nombre AS categoria_nombre, c.descripcion AS categoria_descripcion, c.estado AS categoria_estado "
                +
                "FROM producto p " +
                "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                "WHERE p.estado = 'Activo' AND c.estado ='Activo' ";
        return jdbcTemplate.query(sql, ProductoRowMapper);
    }

    @Override
    public int contarProductosPorCategoria(Integer idCategoria) {
        String sql = "SELECT COUNT(*) FROM producto WHERE id_categoria = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, idCategoria);
    }

    @Override
    public void guardarProducto(Producto producto) {
        String sql = "INSERT INTO producto (codigo, nombre, id_categoria, stock_actual, " +
                "unidad_medida, precio_costo, precio_venta, descripcion, estado) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        jdbcTemplate.update(sql,
                producto.getCodigoProducto(),
                producto.getNombreProducto(),
                producto.getCategoria().getIdCategoria(),
                producto.getStockActualProducto(),
                producto.getUnidadMedidaProducto(),
                producto.getPrecioCostoProducto(),
                producto.getPrecioVentaProducto(),
                producto.getDescripcionProducto(),
                producto.getEstadoProducto());
    }

    @Override
    public Producto buscarProductoPorId(int idProducto) {
        String sql = "SELECT p.*, c.nombre AS categoria_nombre, c.descripcion AS categoria_descripcion, c.estado AS categoria_estado "
                +
                "FROM producto p " +
                "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                "WHERE p.id_producto = ?";

        return jdbcTemplate.queryForObject(sql, ProductoRowMapper, idProducto);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, " +
                "unidad_medida = ?, precio_costo = ?, " +
                "precio_venta = ?, descripcion = ? " +
                "WHERE id_producto = ?";

        jdbcTemplate.update(sql,
                producto.getNombreProducto(),
                producto.getUnidadMedidaProducto(),
                producto.getPrecioCostoProducto(),
                producto.getPrecioVentaProducto(),
                producto.getDescripcionProducto(),
                producto.getIdProducto());
    }

    @Override
    public void eliminarProducto(int idProducto) {
        String sql = "UPDATE producto SET estado = 'Inactivo' WHERE id_producto = ?";
        jdbcTemplate.update(sql, idProducto);
    }

    @Override
    public void activarProducto(int idProducto) {
        String sql = "UPDATE producto SET estado = 'Activo' WHERE id_producto = ?";
        jdbcTemplate.update(sql, idProducto);
    }

    @Override
    public List<Producto> buscarProductosParaMovimiento(String filtro) {
        String sql = "SELECT p.*, c.nombre AS categoria_nombre, c.descripcion AS categoria_descripcion, c.estado AS categoria_estado "
                +
                "FROM producto p " +
                "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                "WHERE (p.codigo LIKE ? OR LOWER(p.nombre) LIKE  LOWER(?)) " +
                "AND p.estado = 'Activo'";
        String parametro = "%" + filtro + "%";
        return jdbcTemplate.query(sql, ProductoRowMapper, parametro, parametro);
    }

    @Override
    public void actualizarStock(int idProducto, int cantidadAjuste) {
        String sql = "UPDATE producto SET stock_actual = stock_actual + ? WHERE id_producto = ?";

        jdbcTemplate.update(sql, cantidadAjuste, idProducto);
    }

    @Override
    public List<Producto> filtrarProducto(String nombre, Integer idCategoria, Integer stockMin, Integer stockMax,
            Integer precioMin, Integer precioMax, String fechaMin, String fechaMax, String estado) {

        StringBuilder sql = new StringBuilder(
                "SELECT p.*, c.nombre AS categoria_nombre, c.descripcion AS categoria_descripcion, c.estado AS categoria_estado "
                        +
                        "FROM producto p " +
                        "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                        "WHERE 1=1 ");

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

        if (precioMin != null) {
            sql.append(" AND p.precio_venta >= ?");
            parametros.add(precioMin);
        }

        if (precioMax != null) {
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

        if (estado != null && !estado.isEmpty() && !estado.equalsIgnoreCase("Todos")) {
            sql.append(" AND p.estado = ?");
            parametros.add(estado);
        }

        return jdbcTemplate.query(sql.toString(), ProductoRowMapper, parametros.toArray());
    }
*/
}