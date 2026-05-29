package Project.ALMXN.Services;

import Project.ALMXN.Repository.CategoriaRepository;
import Project.ALMXN.Repository.ProductoRepository;
import Project.ALMXN.adapters.ProductoAdapter;
import Project.ALMXN.entitys.CategoriaEntity;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.models.Producto;
import java.util.ArrayList;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoAdapter productoAdapter;
    private final ProductoRepository productoRepository;

    public ProductoService(CategoriaRepository categoriaRepository, ProductoAdapter productoAdapter, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoAdapter = productoAdapter;
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodosLosProductos() {

        List<ProductoEntity> entities = productoRepository.findAll();
               return entities.stream()
                       .map(e -> productoAdapter.toModel(e))
                       .collect(Collectors.toList());
    }

    public Producto guardarProducto(Producto producto) {

        ProductoEntity entity;

        Long idCategoria = producto.getCategoria().getIdCategoria();
        CategoriaEntity categoriaEntity = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + idCategoria));

        if (producto.getIdProducto() == null || producto.getIdProducto() == 0) {

            entity = productoAdapter.toEntity(producto, categoriaEntity);

            entity.setStockActualProducto(0);
            entity.setEstadoProducto("Activo");

            categoriaEntity = categoriaRepository.findById(categoriaEntity.getIdCategoria()).get();
            String nombreCat = categoriaEntity.getNombreCategoria();

            String prefijo = nombreCat.substring(0, Math.min(nombreCat.length(), 3)).toUpperCase();

            int cantidadActual = productoRepository.countByCategoriaIdCategoria(categoriaEntity.getIdCategoria());
            int siguienteNumero = cantidadActual + 1;

            String codigoGenerado = String.format("PROD-%s-%03d", prefijo, siguienteNumero);
            producto.setCodigoProducto(codigoGenerado);
        } else {
            entity = productoRepository.findById(producto.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + producto.getIdProducto()));
            entity.setNombreProducto(producto.getNombreProducto());
            entity.setUnidadMedidaProducto(producto.getUnidadMedidaProducto());
            entity.setPrecioCostoProducto(producto.getPrecioCostoProducto());
            entity.setPrecioVentaProducto(producto.getPrecioVentaProducto());
            entity.setDescripcionProducto(producto.getDescripcionProducto());
        }

        ProductoEntity savedEntity = productoRepository.save(entity);

        return productoAdapter.toModel(savedEntity);
    }

    public Producto buscarProductoPorId(Long idProducto) {
        ProductoEntity entity = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + idProducto));
        return productoAdapter.toModel(entity);
    }

    public void eliminarProducto(Long idProducto) {
        ProductoEntity entity = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idProducto));
        entity.setEstadoProducto("Inactivo");
    }

    public void activarProducto(Long idProducto){
        ProductoEntity entity = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idProducto));
        entity.setEstadoProducto("Activo");
    }

    public List<Producto> buscarProductosParaMovimiento(String filtro) {
        String busqueda = filtro.trim();

        List<ProductoEntity> entities = productoRepository
                .findByCodigoContainingOrNombreContainingIgnoreCaseAndEstadoProducto(busqueda, busqueda, "Activo");

        return entities.stream()
                .map(e -> productoAdapter.toModel(e))
                .collect(Collectors.toList());
    }

    public void actualizarStock(Long idProducto, int cantidadAjuste){
        ProductoEntity entity = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idProducto));
        entity.setStockActualProducto(cantidadAjuste);
    }

    public List<Producto> filtrarProducto(String nombre, Long idCategoria, Integer stockMin, Integer stockMax, Integer precioMin,
                                          Integer precioMax, String fechaMin, String fechaMax, String estado){

        Specification<ProductoEntity> spec = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (nombre != null && !nombre.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombreProducto")),
                        "%" + nombre.trim().toLowerCase() + "%"
                ));
            }

            if (idCategoria != null && idCategoria != 0) {
                predicates.add(criteriaBuilder.equal(root.get("categoria").get("idCategoria"), idCategoria));
            }

            // Rango de Stock Mínimo
            if (stockMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stockActualProducto"), stockMin));
            }

            // Rango de Stock Máximo
            if (stockMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("stockActualProducto"), stockMax));
            }

            // Rango de Precio Mínimo
            if (precioMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("precioVentaProducto"), precioMin));
            }

            // Rango de Precio Máximo
            if (precioMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("precioVentaProducto"), precioMax));
            }

            // Rango de Fechas (Ajusta 'fechaCreacionProducto' y los parseos según tu entidad)
            if (fechaMin != null && !fechaMin.isEmpty()) {
                LocalDate inicio = LocalDate.parse(fechaMin);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fechaCreacionProducto"), inicio));
            }
            if (fechaMax != null && !fechaMax.isEmpty()) {
                LocalDate fin = LocalDate.parse(fechaMax);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fechaCreacionProducto"), fin));
            }

            // Filtro por Estado (Igualdad exacta, ej: 'Activo')
            if (estado != null && !estado.isEmpty() && !estado.equalsIgnoreCase("Todos")) {
                predicates.add(criteriaBuilder.equal(root.get("estadoProducto"), estado));
            }

            // Unimos todas las condiciones con un AND lógico
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        // 4. Ejecutamos la búsqueda dinámica nativa de JPA
        List<ProductoEntity> entities = productoRepository.findAll(spec);

        // 5. Convertimos los resultados a tus modelos de dominio
        return entities.stream()
                .map(e -> productoAdapter.toModel(e))
                .collect(Collectors.toList());
    }

}