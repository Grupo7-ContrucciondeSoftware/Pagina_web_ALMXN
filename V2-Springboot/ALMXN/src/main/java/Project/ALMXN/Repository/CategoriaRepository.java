package Project.ALMXN.Repository;

import java.util.ArrayList;
import java.util.List;
import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Movimiento;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaRepository implements CategoriaDAO{

    public final JdbcTemplate jdbcTemplate;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private final RowMapper<Categoria> CategoriaRowMapper = (rs,rowNum) ->{
        return new Categoria(
            rs.getInt("id_categoria"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getString("estado")
        );
    };

    @Override
    public List<Categoria> listaCategorias(){
        String query = "SELECT * FROM categoria WHERE estado = 'Activo' ";
        return jdbcTemplate.query(query, CategoriaRowMapper);
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre, descripcion, estado) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                categoria.getNombreCategoria(),
                categoria.getDescripcionCategoria(),
                categoria.getEstadoCategoria()
        );
    }

    @Override
    public Categoria buscarCategoriaPorId(int idCategoria){
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        return jdbcTemplate.queryForObject(sql, CategoriaRowMapper, idCategoria);
    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ?, descripcion = ? " +
                "WHERE id_categoria = ?";
        jdbcTemplate.update(sql,
                categoria.getNombreCategoria(),
                categoria.getDescripcionCategoria(),
                categoria.getIdCategoria()
        );
    }

    @Override
    public void eliminarCategoria(int idCategoria){
        String sql = "UPDATE categoria SET estado = 'Inactivo' WHERE id_categoria = ?";
        jdbcTemplate.update(sql, idCategoria);

        String sqlProductos = "UPDATE producto SET estado = 'Inactivo' WHERE id_categoria = ?";
        jdbcTemplate.update(sqlProductos, idCategoria);
    }

    @Override
    public void activarCategoria(int idCategoria){
        String sql = "UPDATE categoria SET estado = 'Activo' WHERE id_categoria = ?";
        jdbcTemplate.update(sql, idCategoria);
    }

    @Override
    public List<Categoria> filtrarCategorias(String nombreFiltro, String estadoFiltro) {

        StringBuilder sql = new StringBuilder("SELECT * FROM categoria WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (nombreFiltro != null && !nombreFiltro.trim().isEmpty()) {

            sql.append(" AND LOWER(nombre) LIKE LOWER(?)");
            parametros.add("%" + nombreFiltro.trim() + "%");
        }

        if (estadoFiltro != null && !estadoFiltro.isEmpty() && !estadoFiltro.equalsIgnoreCase("Todos")){
            sql.append(" AND estado = ?");
            parametros.add(estadoFiltro);
        }

        return jdbcTemplate.query(sql.toString(), CategoriaRowMapper, parametros.toArray());
    }
}
