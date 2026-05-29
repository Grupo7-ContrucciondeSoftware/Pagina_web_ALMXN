package Project.ALMXN.Repository;

import Project.ALMXN.entitys.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>, JpaSpecificationExecutor<UsuarioEntity> {

    Optional<UsuarioEntity> findByCorreoAndContraseñaAndEstado(String correo, String contrasena, String estado);
/*
    @Override
    public Usuario buscarPorCorreoYContrasena(String correo, String contraseña) {
        String sql = "SELECT * FROM usuario WHERE correo = ? AND contraseña = ? AND estado = 'Activo' ";
        List<Usuario> resultado = jdbcTemplate.query(sql, UsuarioRowMapper, correo, contraseña);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

*/
}