package Project.ALMXN.Services;

import Project.ALMXN.models.Producto;
import java.util.List;

public interface ProductoService {

    List<Producto> obtenerTodosLosProductos();

    int contarProductosPorCategoria(Integer idCategoria);

    void guardarProducto(Producto producto);

    Producto buscarProductoPorId(int idProducto);

    void actualizarProducto(Producto producto);

    void eliminarProducto(int idProducto);

    List<Producto> buscarProductosParaMovimiento(String filtro);

    void actualizarStock(int idProducto, int cantidadAjuste);

    List<Producto> filtrarProducto(String nombre, Integer idCategoria, Integer stockMin, Integer stockMax, Integer precioMin, Integer precioMax, String fechaMin, String fechaMax, String estado);

    void activarProducto(int idProducto);

}