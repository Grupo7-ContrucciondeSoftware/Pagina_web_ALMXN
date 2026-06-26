package Project.ALMXN.Repository;

import Project.ALMXN.entitys.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>, JpaSpecificationExecutor<UsuarioEntity> {

    Optional<UsuarioEntity> findByCorreoAndContrasenaAndEstado(String correo, String contrasena, String estado);

    boolean existsByCorreo(String correo);

    boolean existsByCorreoAndIdUsuarioNot(String correo, Long idUsuario);
}