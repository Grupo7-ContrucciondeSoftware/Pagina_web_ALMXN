package Project.ALMXN.Repository;

import Project.ALMXN.entitys.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>, JpaSpecificationExecutor<CategoriaEntity> {
    // RN 02: buscar categoría por nombre (para validar duplicados)
    Optional<CategoriaEntity> findByNombreCategoriaIgnoreCase(String nombreCategoria);
}