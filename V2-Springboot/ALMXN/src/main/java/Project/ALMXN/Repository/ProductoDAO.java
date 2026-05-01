package Project.ALMXN.Repository;

import Project.ALMXN.models.Producto;
import java.util.List;

public interface ProductoDAO {

    List<Producto> listaProductos();

    void guardarProducto(Producto producto);

    Producto buscarProductoPorId(int idProducto);

    void actualizarProducto(Producto producto);

    void eliminarProducto(int idProducto);

    List<Producto> buscarProductosParaMovimiento(String filtro);

}