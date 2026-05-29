package Project.ALMXN.Repository;

import java.util.List;

import Project.ALMXN.entitys.DetalleMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimientoEntity, Long> {

    List<DetalleMovimientoEntity> findByMovimientoIdMovimiento(Long idMovimiento);
}