package Project.ALMXN.Repository;

import Project.ALMXN.models.Producto;
import java.util.List;

public interface ProductoDAO {

    List<Producto> listaProductos();

    int contarProductosPorCategoria(Integer idCategoria);

    void guardarProducto(Producto producto);

    Producto buscarProductoPorId(int idProducto);

    void actualizarProducto(Producto producto);

    void eliminarProducto(int idProducto);

    void activarProducto(int idProducto);

    List<Producto> buscarProductosParaMovimiento(String filtro);

    void actualizarStock(int idProducto, int cantidadAjuste);

    List<Producto> filtrarProducto(String nombre, Integer idCategoria, Integer stockMin, Integer stockMax, Integer precioMin, Integer precioMax, String fechaMin, String fechaMax, String estado);
}