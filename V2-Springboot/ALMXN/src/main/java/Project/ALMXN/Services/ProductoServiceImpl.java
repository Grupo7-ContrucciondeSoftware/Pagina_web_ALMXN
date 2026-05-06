package Project.ALMXN.Services;

import Project.ALMXN.Repository.CategoriaDAO;
import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Producto;
import Project.ALMXN.Repository.ProductoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Autowired
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

        if (producto.getIdProducto() == null) {

            producto.setStockActualProducto(0);

            producto.setEstadoProducto("Activo");

            // 3. Obtener datos de la categoría para el código
            Categoria categoria = categoriaDAO.buscarCategoriaPorId(producto.getCategoria().getIdCategoria());
            String nombreCat = categoria.getNombreCategoria();

            // Extraer las primeras 3 letras
            String prefijo = nombreCat.substring(0, Math.min(nombreCat.length(), 3)).toUpperCase();

            // 4. Contar productos y generar el correlativo
            int cantidadActual = productoDAO.contarProductosPorCategoria(categoria.getIdCategoria());
            int siguienteNumero = cantidadActual + 1;

            String codigoGenerado = String.format("PROD-%s-%03d", prefijo, siguienteNumero);

            // 5. Asignar el código final
            producto.setCodigoProducto(codigoGenerado);

            productoDAO.guardarProducto(producto);

        } else {
            productoDAO.actualizarProducto(producto);
        }

    }

    @Override
    public int contarProductosPorCategoria(Integer idCategoria){
        return productoDAO.contarProductosPorCategoria(idCategoria);
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