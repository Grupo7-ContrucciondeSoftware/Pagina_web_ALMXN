package Project.ALMXN.Repository;

import Project.ALMXN.entitys.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>, JpaSpecificationExecutor<ProductoEntity> {

    int countByCategoriaIdCategoria(Long idCategoria);

    List<ProductoEntity> findByCategoriaIdCategoria(Long idCategoria);

    List<ProductoEntity> findByCodigoProductoContainingIgnoreCaseAndEstadoProductoOrNombreProductoContainingIgnoreCaseAndEstadoProducto(
            String codigo, String estado1, String nombre, String estado2);

    Optional<ProductoEntity> findByNombreProductoIgnoreCase(String nombreProducto);

}