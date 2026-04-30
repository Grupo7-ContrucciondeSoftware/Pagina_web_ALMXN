package Project.ALMXN.Repository;

import java.util.List;
import Project.ALMXN.models.Categoria;
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
            rs.getString("descripcion")
        );
    };

    @Override
    public List<Categoria> listaCategorias(){
        String query = "SELECT * FROM categoria";
        return jdbcTemplate.query(query, CategoriaRowMapper);
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql,
                categoria.getNombreCategoria(),
                categoria.getDescripcionCategoria()
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
}
