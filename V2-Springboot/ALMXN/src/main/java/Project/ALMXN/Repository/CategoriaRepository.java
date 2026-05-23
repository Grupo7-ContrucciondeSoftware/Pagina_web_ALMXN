package Project.ALMXN.Repository;

import Project.ALMXN.entitys.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{

    /*
    @Override
    public void actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ?, descripcion = ? " +
                "WHERE id_categoria = ?";
        jdbcTemplate.update(sql,
                categoria.getNombreCategoria(),
                categoria.getDescripcionCategoria(),
                categoria.getIdCategoria());
    }

    @Override
    public void eliminarCategoria(int idCategoria) {
        String sql = "UPDATE categoria SET estado = 'Inactivo' WHERE id_categoria = ?";
        jdbcTemplate.update(sql, idCategoria);

        String sqlProductos = "UPDATE producto SET estado = 'Inactivo' WHERE id_categoria = ?";
        jdbcTemplate.update(sqlProductos, idCategoria);
    }

    @Override
    public void activarCategoria(int idCategoria) {
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

        if (estadoFiltro != null && !estadoFiltro.isEmpty() && !estadoFiltro.equalsIgnoreCase("Todos")) {
            sql.append(" AND estado = ?");
            parametros.add(estadoFiltro);
        }

        return jdbcTemplate.query(sql.toString(), CategoriaRowMapper, parametros.toArray());
    }*/
}
