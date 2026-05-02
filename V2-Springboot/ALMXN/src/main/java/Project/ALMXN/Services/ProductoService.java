package Project.ALMXN.Services;

import Project.ALMXN.models.Producto;
import java.util.List;

public interface ProductoService {

    List<Producto> obtenerTodosLosProductos();

    void guardarProducto(Producto producto);

    Producto buscarProductoPorId(int idProducto);

    void actualizarProducto(Producto producto);

    void eliminarProducto(int idProducto);

    List<Producto> buscarProductosParaMovimiento(String filtro);

    void actualizarStock(int idProducto, int cantidadAjuste);

}