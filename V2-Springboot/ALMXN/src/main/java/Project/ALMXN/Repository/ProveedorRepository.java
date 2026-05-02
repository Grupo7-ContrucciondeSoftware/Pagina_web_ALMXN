package Project.ALMXN.Repository;

import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Proveedor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProveedorRepository implements ProveedorDAO{

    public final JdbcTemplate jdbcTemplate;

    public ProveedorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Proveedor> ProveedorRowMapper = (rs, rowNum) ->{
        return new Proveedor(
                rs.getInt("id_proveedor"),
                rs.getString("ruc"),
                rs.getString("razon_social"),
                rs.getString("telefono"),
                rs.getString("correo")
        );
    };

    @Override
    public List<Proveedor> listaProveedores(){
        String sql = "SELECT * FROM proveedor";
        return jdbcTemplate.query(sql, ProveedorRowMapper);
    }

    @Override
    public void guardarProveedor(Proveedor proveedor){
        String sql = "INSERT INTO proveedor (ruc, razon_social, telefono, correo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                proveedor.getRucProveedor(),
                proveedor.getRazonSocialProveedor(),
                proveedor.getTelefonoProveedor(),
                proveedor.getCorreoProveedor()
        );
    }

    @Override
    public Proveedor buscarProveedorPorId(int idProveedor){
        String sql = "SELECT * FROM proveedor WHERE id_proveedor = ?";
        return jdbcTemplate.queryForObject(sql, ProveedorRowMapper, idProveedor);
    }

    @Override
    public void actualizarProveedor(Proveedor proveedor){
        String sql = "UPDATE proveedor SET ruc = ?, razon_social = ?, telefono = ?, correo = ? WHERE id_proveedor = ?";
        jdbcTemplate.update(sql,
                proveedor.getRucProveedor(),
                proveedor.getRazonSocialProveedor(),
                proveedor.getTelefonoProveedor(),
                proveedor.getCorreoProveedor(),
                proveedor.getIdProveedor()
        );
    }

    @Override
    public List<Proveedor> filtrarProveedor(String razonSocial, String ruc, Integer telefono) {

        StringBuilder sql = new StringBuilder("SELECT * FROM proveedor WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (razonSocial != null && !razonSocial.trim().isEmpty()) {
            sql.append(" AND LOWER(razon_social) LIKE LOWER(?)");
            parametros.add("%" + razonSocial.trim() + "%");
        }

        if (ruc != null && !ruc.trim().isEmpty()){
            sql.append(" AND LOWER(ruc) LIKE LOWER(?)");
            parametros.add("%" + ruc.trim() + "%");
        }

        if (telefono != null){
            sql.append(" AND CAST(telefono AS VARCHAR) LIKE ?");
            parametros.add("%" + telefono + "%");
        }

        return jdbcTemplate.query(sql.toString(), ProveedorRowMapper, parametros.toArray());
    }

}
