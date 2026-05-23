package Project.ALMXN.Services;

import Project.ALMXN.Repository.CategoriaRepository;
import Project.ALMXN.Repository.ProductoRepository;
import Project.ALMXN.adapters.ProductoAdapter;
import Project.ALMXN.entitys.CategoriaEntity;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Producto;
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

    public Producto guardarProducto(Producto producto, CategoriaEntity categoriaEntity) {

        ProductoEntity entity = productoAdapter.toEntity(producto, categoriaEntity);

        if (producto.getIdProducto() == null || producto.getIdProducto() == 0) {
                    entity = productoAdapter.toEntity(producto, categoriaEntity);
                    entity.setStockActualProducto(0);
                    entity.setEstadoProducto("Activo");

                    categoriaEntity = categoriaRepository.findById(categoriaEntity.getIdCategoria()).get();
                    String

        //        } else {
        //            entity = categoriaRepository.findById(categoria.getIdCategoria())
        //                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoria.getIdCategoria()));
        //            entity.setNombreCategoria(categoria.getNombreCategoria());
        //            entity.setDescripcionCategoria(categoria.getDescripcionCategoria());
        //        }
        //        CategoriaEntity savedEntity = categoriaRepository.save(entity);

        /*if (producto.getIdProducto() == null) {

            producto.setStockActualProducto(0);

            producto.setEstadoProducto("Activo");

            // Lógica para el codigo autogenerado
            Categoria categoria = categoriaRepository.findById();
            String nombreCat = categoria.getNombreCategoria();

            String prefijo = nombreCat.substring(0, Math.min(nombreCat.length(), 3)).toUpperCase();

            int cantidadActual = productoDAO.contarProductosPorCategoria(categoria.getIdCategoria());
            int siguienteNumero = cantidadActual + 1;

            String codigoGenerado = String.format("PROD-%s-%03d", prefijo, siguienteNumero);

            producto.setCodigoProducto(codigoGenerado);

            productoDAO.guardarProducto(producto);

        } else {
            productoDAO.actualizarProducto(producto);
        }*/

    }

    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarProductoPorId(id);
    }

    public void eliminarProducto(int idProducto) {
        productoDAO.eliminarProducto(idProducto);
    }

    public void activarProducto(int idProducto){
        productoDAO.activarProducto(idProducto);
    }

    public List<Producto> buscarProductosParaMovimiento(String filtro) {
        return productoDAO.buscarProductosParaMovimiento(filtro);
    }

    public void actualizarStock(int idProducto, int cantidadAjuste){
        productoDAO.actualizarStock(idProducto, cantidadAjuste);
    }

    public List<Producto> filtrarProducto(String nombre, Integer idCategoria, Integer stockMin, Integer stockMax, Integer precioMin, Integer precioMax, String fechaMin, String fechaMax, String estado){
        return productoDAO.filtrarProducto(nombre, idCategoria, stockMin, stockMax, precioMin, precioMax, fechaMin, fechaMax, estado);
    }
}