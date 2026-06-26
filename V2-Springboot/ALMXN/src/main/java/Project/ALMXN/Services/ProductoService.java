package Project.ALMXN.Services;

import Project.ALMXN.Repository.CategoriaRepository;
import Project.ALMXN.Repository.ProductoRepository;
import Project.ALMXN.adapters.ProductoAdapter;
import Project.ALMXN.entitys.CategoriaEntity;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.models.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        return filtrarProducto(null, null, null, null, null, "Activo");
    }

    @Transactional
    public Producto guardarProducto(Producto producto) {
        String nombre = producto.getNombreProducto();
        Long id = producto.getIdProducto();
        boolean esNuevo = (id == null || id == 0);

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }

        productoRepository.findByNombreProductoIgnoreCase(nombre.trim()).ifPresent(e -> {
            if (esNuevo || !e.getIdProducto().equals(id)) {
                throw new IllegalArgumentException("Ya existe un producto registrado con el nombre: " + nombre.trim());
            }
        });

        ProductoEntity entity;

        if (esNuevo) {
            if (producto.getCategoria() == null || producto.getCategoria().getIdCategoria() == null) {
                throw new RuntimeException("La categoría es obligatoria para registrar un nuevo producto.");
            }

            Long idCategoria = producto.getCategoria().getIdCategoria();
            CategoriaEntity categoriaEntity = categoriaRepository.findById(idCategoria)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + idCategoria));

            producto.setFechaCreacionProducto(LocalDate.now());
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
            entity = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

            if (producto.getCategoria() != null && producto.getCategoria().getIdCategoria() != null) {
                Long idCategoria = producto.getCategoria().getIdCategoria();
                CategoriaEntity nuevaCategoria = categoriaRepository.findById(idCategoria)
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + idCategoria));
                entity.setCategoria(nuevaCategoria);
            }

            entity.setNombreProducto(nombre.trim());
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
    public void activarProducto(Long idProducto) {
        ProductoEntity entity = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idProducto));
        entity.setEstadoProducto("Activo");
        productoRepository.save(entity);
    }

    public List<Producto> buscarProductosParaMovimiento(String filtro) {
        if (filtro == null) {
            return new ArrayList<>();
        }

        // 1. Traemos todos los productos activos
        List<ProductoEntity> todos = productoRepository.findAll();
        List<Producto> resultado = new ArrayList<>();
        String busqueda = filtro.trim().toLowerCase();

        // 2. Filtramos los que coincidan con código o nombre y estén activos
        for (ProductoEntity producto : todos) {
            if (!producto.getEstadoProducto().equals("Activo")) {
                continue;
            }

            boolean coincideCodigo = producto.getCodigoProducto().toLowerCase().contains(busqueda);
            boolean coincideNombre = producto.getNombreProducto().toLowerCase().contains(busqueda);

            if (coincideCodigo || coincideNombre) {
                resultado.add(productoAdapter.toModel(producto));
            }
        }

        return resultado;
    }

    public List<Producto> filtrarProducto(String nombre, Long idCategoria, Integer stock, Double precio,
                                          String fecha, String estado) {

        // 1. Traemos todos los productos de la base de datos
        List<ProductoEntity> todos = productoRepository.findAll();

        // 2. Lista donde guardaremos los que pasen los filtros
        List<ProductoEntity> resultado = new ArrayList<>();

        // 3. Recorremos uno por uno y aplicamos los filtros
        for (ProductoEntity producto : todos) {

            // Filtro por nombre: verifica que contenga el texto
            if (nombre != null && !nombre.trim().isEmpty()) {
                boolean contieneNombre = producto.getNombreProducto()
                        .toLowerCase()
                        .contains(nombre.trim().toLowerCase());
                if (!contieneNombre) {
                    continue;
                }
            }

            // Filtro por categoría: verifica que coincida el ID
            if (idCategoria != null && idCategoria != 0) {
                if (producto.getCategoria() == null ||
                        !producto.getCategoria().getIdCategoria().equals(idCategoria)) {
                    continue;
                }
            }

            // Filtro por stock: verifica que coincida exacto
            if (stock != null) {
                if (producto.getStockActualProducto() != stock) {
                    continue;
                }
            }

            // Filtro por precio: verifica que coincida exacto
            if (precio != null) {
                if (producto.getPrecioVentaProducto() != precio) {
                    continue;
                }
            }

            // Filtro por fecha: verifica que coincida exacto
            if (fecha != null && !fecha.isEmpty()) {
                LocalDate fechaLocal = LocalDate.parse(fecha);
                if (!producto.getFechaCreacionProducto().equals(fechaLocal)) {
                    continue;
                }
            }

            // Filtro por estado
            if (estado == null || estado.trim().isEmpty()) {
                // Sin estado → solo "Activo"
                if (!producto.getEstadoProducto().equals("Activo")) {
                    continue;
                }
            } else if (!estado.equalsIgnoreCase("Todos")) {
                // Estado específico → filtramos exacto
                if (!producto.getEstadoProducto().equalsIgnoreCase(estado)) {
                    continue;
                }
            }

            resultado.add(producto);
        }

        // 4. Convertimos las entidades a modelos y retornamos
        List<Producto> productos = new ArrayList<>();
        for (ProductoEntity entity : resultado) {
            productos.add(productoAdapter.toModel(entity));
        }
        return productos;
    }
}