package Project.ALMXN.Services;

import Project.ALMXN.Repository.CategoriaDAO;
import Project.ALMXN.Repository.CategoriaRepository;
import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Producto;
import Project.ALMXN.Repository.ProductoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoDAO productoDAO;

    @Autowired
    public ProductoServiceImpl(ProductoDAO productoDAO, CategoriaRepository categoriaRepository) {
        this.productoDAO = productoDAO;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.listaProductos();
    }

    @Override
    public void guardarProducto(Producto producto) {

        if (producto.getIdProducto() == null) {

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
        }

    }

    @Override
    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarProductoPorId(id);
    }

    @Override
    public void eliminarProducto(int idProducto) {
        productoDAO.eliminarProducto(idProducto);
    }

    @Override
    public void activarProducto(int idProducto){
        productoDAO.activarProducto(idProducto);
    }

    @Override
    public List<Producto> buscarProductosParaMovimiento(String filtro) {
        return productoDAO.buscarProductosParaMovimiento(filtro);
    }

    @Override
    public void actualizarStock(int idProducto, int cantidadAjuste){
        productoDAO.actualizarStock(idProducto, cantidadAjuste);
    }

    @Override
    public List<Producto> filtrarProducto(String nombre, Integer idCategoria, Integer stockMin, Integer stockMax, Integer precioMin, Integer precioMax, String fechaMin, String fechaMax, String estado){
        return productoDAO.filtrarProducto(nombre, idCategoria, stockMin, stockMax, precioMin, precioMax, fechaMin, fechaMax, estado);
    }

}