package Project.ALMXN.Services;

import Project.ALMXN.Repository.CategoriaRepository;
import Project.ALMXN.Repository.ProductoRepository;
import Project.ALMXN.adapters.ProductoAdapter;
import Project.ALMXN.entitys.CategoriaEntity;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.models.Producto;
import java.util.ArrayList;

import jakarta.transaction.Transactional;
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
        return filtrarProducto(null, null, null, null, null, null, null, null, "Activo");
    }

    @Transactional
    public Producto guardarProducto(Producto producto) {

        ProductoEntity entity;

        if (producto.getIdProducto() == null || producto.getIdProducto() == 0) {

            if (producto.getCategoria() == null || producto.getCategoria().getIdCategoria() == null) {
                throw new RuntimeException("La categoría es obligatoria para registrar un nuevo producto.");
            }

            Long idCategoria = producto.getCategoria().getIdCategoria();
            CategoriaEntity categoriaEntity = categoriaRepository.findById(idCategoria)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + idCategoria));

            entity = productoAdapter.toEntity(producto, categoriaEntity);

            entity.setStockActualProducto(0);
            entity.setEstadoProducto("Activo");

            String nombreCat = categoriaEntity.getNombreCategoria();
            String prefijo = nombreCat.substring(0, Math.min(nombreCat.length(), 3)).toUpperCase();

            int cantidadActual = productoRepository.countByCategoriaIdCategoria(categoriaEntity.getIdCategoria());
            int siguienteNumero = cantidadActual + 1;

            String codigoGenerado = String.format("PROD-%s-%03d", prefijo, siguienteNumero);

            entity.setCodigoProducto(codigoGenerado);
            producto.setCodigoProducto(codigoGenerado);

        } else {
            entity = productoRepository.findById(producto.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + producto.getIdProducto()));

            if (producto.getCategoria() != null && producto.getCategoria().getIdCategoria() != null) {
                Long idCategoria = producto.getCategoria().getIdCategoria();
                CategoriaEntity nuevaCategoria = categoriaRepository.findById(idCategoria)
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + idCategoria));
                entity.setCategoria(nuevaCategoria);
            }

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

    @Transactional
    public void eliminarProducto(Long idProducto) {
        ProductoEntity entity = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idProducto));
        entity.setEstadoProducto("Inactivo");
        productoRepository.save(entity);
    }

    @Transactional
    public void activarProducto(Long idProducto){
        ProductoEntity entity = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idProducto));
        entity.setEstadoProducto("Activo");
        productoRepository.save(entity);
    }

    public List<Producto> buscarProductosParaMovimiento(String filtro) {
        if (filtro == null) {
            return new ArrayList<>();
        }
        String busqueda = filtro.trim();
        String estadoRequerido = "Activo";

        List<ProductoEntity> entities = productoRepository
                .findByCodigoProductoContainingIgnoreCaseAndEstadoProductoOrNombreProductoContainingIgnoreCaseAndEstadoProducto(
                        busqueda, estadoRequerido, busqueda, estadoRequerido
                );

        return entities.stream()
                .map(e -> productoAdapter.toModel(e))
                .collect(Collectors.toList());
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

            if (stockMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stockActualProducto"), stockMin));
            }

            if (stockMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("stockActualProducto"), stockMax));
            }

            if (precioMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("precioVentaProducto"), precioMin));
            }

            if (precioMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("precioVentaProducto"), precioMax));
            }

            if (fechaMin != null && !fechaMin.isEmpty()) {
                LocalDate inicio = LocalDate.parse(fechaMin);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fechaCreacionProducto"), inicio));
            }
            if (fechaMax != null && !fechaMax.isEmpty()) {
                LocalDate fin = LocalDate.parse(fechaMax);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fechaCreacionProducto"), fin));
            }

            if (estado == null || estado.trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("estadoProducto"), "Activo"));
            } else if (!estado.equalsIgnoreCase("Todos")) {
                predicates.add(criteriaBuilder.equal(root.get("estadoProducto"), estado));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<ProductoEntity> entities = productoRepository.findAll(spec);

        return entities.stream()
                .map(e -> productoAdapter.toModel(e))
                .collect(Collectors.toList());
    }

}