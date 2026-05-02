package Project.ALMXN.Services;

import Project.ALMXN.models.Producto;
import Project.ALMXN.Repository.ProductoDAO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDAO productoDAO;

    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.listaProductos();
    }

    @Override
    public void guardarProducto(Producto producto) {
        productoDAO.guardarProducto(producto);
    }

    @Override
    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarProductoPorId(id);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        productoDAO.actualizarProducto(producto);
    }

    @Override
    public void eliminarProducto(int idProducto) {
        productoDAO.eliminarProducto(idProducto);
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
    public List<Producto> filtrarProducto(String nombre, Integer idCategoria, Integer stockMin, Integer stockMax, Integer precioMin, Integer precioMax, String fechaMin, String fechaMax){
        return productoDAO.filtrarProducto(nombre, idCategoria, stockMin, stockMax, precioMin, precioMax, fechaMin, fechaMax);
    }

}