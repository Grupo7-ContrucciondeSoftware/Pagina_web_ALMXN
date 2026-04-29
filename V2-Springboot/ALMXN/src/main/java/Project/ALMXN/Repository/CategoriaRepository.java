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
    public void guardarCategoria(Categoria Categoria) {
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, Categoria.getNombreCategoria(), Categoria.getDescripcionCategoria());
    }

}
