package Project.ALMXN.Repository;

import Project.ALMXN.models.Producto;
import java.util.List;

public interface ProductoDAO {

    List<Producto> listaProductos();

    public void guardarProducto(Producto producto);

    public Producto buscarProductoPorId(int idProducto);

    public void actualizarProducto(Producto producto);
}