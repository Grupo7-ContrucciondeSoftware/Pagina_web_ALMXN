package Project.ALMXN.Repository;

import Project.ALMXN.entitys.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {

    Optional<ProveedorEntity> findByRucProveedor(String rucProveedor);

    Optional<ProveedorEntity> findByRazonSocialProveedor(String razonSocialProveedor);

    Optional<ProveedorEntity> findByTelefonoProveedor(String telefonoProveedor);

    Optional<ProveedorEntity> findByCorreoProveedor(String correoProveedor);

}