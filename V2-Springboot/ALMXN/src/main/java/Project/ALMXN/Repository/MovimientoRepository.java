package Project.ALMXN.Repository;

import Project.ALMXN.entitys.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long>, JpaSpecificationExecutor<MovimientoEntity> {

}
