package Project.ALMXN.Repository;

import java.util.List;

import Project.ALMXN.entitys.DetalleMovimientoEntity;
import Project.ALMXN.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimientoEntity, Long> {

    List<DetalleMovimientoEntity> findByMovimientoIdMovimiento(Long idMovimiento);
}