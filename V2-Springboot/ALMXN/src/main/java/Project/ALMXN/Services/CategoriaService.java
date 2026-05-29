package Project.ALMXN.Services;

import Project.ALMXN.Repository.CategoriaRepository;
import Project.ALMXN.Repository.ProductoRepository;
import Project.ALMXN.adapters.CategoriaAdapter;
import Project.ALMXN.entitys.CategoriaEntity;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.models.Categoria;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;


@Service
public class CategoriaService{

    private final CategoriaAdapter categoriaAdapter;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaService(CategoriaAdapter categoriaAdapter, CategoriaRepository categoriaRepository, ProductoRepository productoRepository){
        this.categoriaAdapter = categoriaAdapter;
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    public List<Categoria> obtenerTodasLasCategorias(){

        List<CategoriaEntity> entities = categoriaRepository.findAll();

        return entities.stream()
                .map(e -> categoriaAdapter.toModel(e))
                .collect(Collectors.toList());
    }

    public Categoria guardarCategoria(Categoria categoria) {

        CategoriaEntity entity;

        if (categoria.getIdCategoria() == null || categoria.getIdCategoria() == 0) {
            entity = categoriaAdapter.toEntity(categoria);
            entity.setEstadoCategoria("Activo");
        } else {
            entity = categoriaRepository.findById(categoria.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoria.getIdCategoria()));
            entity.setNombreCategoria(categoria.getNombreCategoria());
            entity.setDescripcionCategoria(categoria.getDescripcionCategoria());
        }
        CategoriaEntity savedEntity = categoriaRepository.save(entity);
        return categoriaAdapter.toModel(savedEntity);
    }

    public Categoria buscarCategoriaPorId(Long idCategoria){
        CategoriaEntity entity = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idCategoria));
        return categoriaAdapter.toModel(entity);
    }

    public void eliminarCategoria(Long idCategoria){
        CategoriaEntity entity = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idCategoria));
        entity.setEstadoCategoria("Inactivo");

        List<ProductoEntity> productos = productoRepository.findByCategoriaIdCategoria(idCategoria);
        productos.forEach(producto -> producto.setEstadoProducto("Inactivo"));
        productoRepository.saveAll(productos);
    }

    public List<Categoria> filtrarCategorias(String nombreFiltro, String estadoFiltro){
        CategoriaEntity filtro = new CategoriaEntity();

        if (nombreFiltro != null && !nombreFiltro.trim().isEmpty()) {
            filtro.setNombreCategoria(nombreFiltro.trim());
        }

        if (estadoFiltro != null && !estadoFiltro.isEmpty() && !estadoFiltro.equalsIgnoreCase("Todos")) {
            filtro.setEstadoCategoria(estadoFiltro);
        }

        // 2. Configuramos las reglas del matcher (Ignorar mayúsculas/minúsculas y aplicar el LIKE)
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues();

        // 3. Empaquetamos el molde y las reglas
        Example<CategoriaEntity> example = Example.of(filtro, matcher);

        // 4. Ejecutamos la búsqueda dinámica nativa de JPA
        List<CategoriaEntity> entities = categoriaRepository.findAll(example);

        // 5. Convertimos los resultados a tus modelos de dominio
        return entities.stream()
                .map(e -> categoriaAdapter.toModel(e))
                .collect(Collectors.toList());
    }

    public void activarCategoria(Long idCategoria){
        CategoriaEntity entity = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + idCategoria));
        entity.setEstadoCategoria("Activo");
    }

}
