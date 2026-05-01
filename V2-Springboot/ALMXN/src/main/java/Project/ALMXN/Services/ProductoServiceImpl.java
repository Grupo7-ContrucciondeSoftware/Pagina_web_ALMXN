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
    public void guardarProducto(Producto producto){
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
    public void eliminarProducto(int idProducto){
        productoDAO.eliminarProducto(idProducto);
    }

    @Override
    public List<Producto> buscarProductosPorFiltro(String filtro){
        return productoDAO.buscarProductosPorFiltro(filtro);
    }

}