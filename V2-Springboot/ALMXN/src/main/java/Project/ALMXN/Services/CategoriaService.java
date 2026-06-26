package Project.ALMXN.Services;

import Project.ALMXN.Repository.CategoriaRepository;
import Project.ALMXN.Repository.ProductoRepository;
import Project.ALMXN.adapters.CategoriaAdapter;
import Project.ALMXN.entitys.CategoriaEntity;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.models.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaAdapter categoriaAdapter;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaService(CategoriaAdapter categoriaAdapter, CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaAdapter = categoriaAdapter;
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    public List<Categoria> obtenerTodasLasCategorias() {
        return filtrarCategorias(null, "Activo");
    }

    @Transactional
    public Categoria guardarCategoria(Categoria categoria) {
        CategoriaEntity entity;

        if (categoria.getIdCategoria() == null || categoria.getIdCategoria() == 0) {
            Optional<CategoriaEntity> existente = categoriaRepository
                    .findByNombreCategoriaIgnoreCase(categoria.getNombreCategoria().trim());
            if (existente.isPresent()) {
                throw new IllegalArgumentException(
                        "Ya existe una categoría registrada con el nombre: " + categoria.getNombreCategoria());
            }
            entity = categoriaAdapter.toEntity(categoria);
            entity.setEstadoCategoria("Activo");
        } else {
            entity = categoriaRepository.findById(categoria.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoria.getIdCategoria()));

            Optional<CategoriaEntity> existente = categoriaRepository
                    .findByNombreCategoriaIgnoreCase(categoria.getNombreCategoria().trim());
            if (existente.isPresent() && !existente.get().getIdCategoria().equals(categoria.getIdCategoria())) {
                throw new IllegalArgumentException(
                        "Ya existe otra categoría registrada con el nombre: " + categoria.getNombreCategoria());
            }
            entity.setNombreCategoria(categoria.getNombreCategoria());
            entity.setDescripcionCategoria(categoria.getDescripcionCategoria());
        }

        CategoriaEntity savedEntity = categoriaRepository.save(entity);
        return categoriaAdapter.toModel(savedEntity);
    }

    public Categoria buscarCategoriaPorId(Long idCategoria) {
        CategoriaEntity entity = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idCategoria));
        return categoriaAdapter.toModel(entity);
    }

    @Transactional
    public void eliminarCategoria(Long idCategoria) {
        CategoriaEntity entity = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idCategoria));
        entity.setEstadoCategoria("Inactivo");
        categoriaRepository.save(entity);

        List<ProductoEntity> productos = productoRepository.findByCategoriaIdCategoria(idCategoria);
        productos.forEach(producto -> producto.setEstadoProducto("Inactivo"));
        productoRepository.saveAll(productos);
    }

    public List<Categoria> filtrarCategorias(String nombreFiltro, String estadoFiltro) {

        // 1. Traemos todas las categorías de la base de datos
        List<CategoriaEntity> todas = categoriaRepository.findAll();

        // 2. Lista donde guardaremos los que pasen los filtros
        List<CategoriaEntity> resultado = new ArrayList<>();

        // 3. Recorremos una por una y aplicamos los filtros
        for (CategoriaEntity categoria : todas) {

            // Filtro por nombre: si se mandó un nombre, verificamos que lo contenga
            if (nombreFiltro != null && !nombreFiltro.trim().isEmpty()) {
                boolean contieneNombre = categoria.getNombreCategoria()
                        .toLowerCase()
                        .contains(nombreFiltro.trim().toLowerCase());
                if (!contieneNombre) {
                    continue;
                }
            }

            // Filtro por estado
            if (estadoFiltro == null || estadoFiltro.trim().isEmpty()) {
                // Sin estado → solo "Activo"
                if (!categoria.getEstadoCategoria().equals("Activo")) {
                    continue;
                }
            } else if (!estadoFiltro.equalsIgnoreCase("Todos")) {
                // Estado específico → filtramos exacto
                if (!categoria.getEstadoCategoria().equalsIgnoreCase(estadoFiltro)) {
                    continue;
                }
            }

            resultado.add(categoria);
        }

        // 4. Convertimos las entidades a modelos y retornamos
        List<Categoria> categorias = new ArrayList<>();
        for (CategoriaEntity entity : resultado) {
            categorias.add(categoriaAdapter.toModel(entity));
        }
        return categorias;
    }

    @Transactional
    public void activarCategoria(Long idCategoria) {
        CategoriaEntity entity = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idCategoria));
        entity.setEstadoCategoria("Activo");
        categoriaRepository.save(entity);
    }
}