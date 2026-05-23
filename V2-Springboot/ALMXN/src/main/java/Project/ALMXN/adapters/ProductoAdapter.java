package Project.ALMXN.adapters;

import Project.ALMXN.entitys.CategoriaEntity;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.models.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoAdapter {

    private CategoriaAdapter categoriaAdapter;

    public ProductoEntity toEntity(Producto producto, CategoriaEntity categoriaEntity){
        if(producto == null){
            return null;
        }
        ProductoEntity productoEntity = new ProductoEntity();

        productoEntity.setIdProducto(producto.getIdProducto());
        productoEntity.setCodigoProducto(producto.getCodigoProducto());
        productoEntity.setFechaCreacionProducto(producto.getFechaCreacionProducto());
        productoEntity.setNombreProducto(producto.getNombreProducto());
        productoEntity.setCategoria(categoriaEntity);
        productoEntity.setStockActualProducto(producto.getStockActualProducto());
        productoEntity.setUnidadMedidaProducto(producto.getUnidadMedidaProducto());
        productoEntity.setPrecioCostoProducto(producto.getPrecioCostoProducto());
        productoEntity.setPrecioVentaProducto(producto.getPrecioVentaProducto());
        productoEntity.setDescripcionProducto(producto.getDescripcionProducto());
        productoEntity.setEstadoProducto(producto.getEstadoProducto());

        return productoEntity;
    }

    public Producto toModel(ProductoEntity productoEntity){
        if(productoEntity == null){
            return null;
        }

        Producto producto = new Producto();

        producto.setIdProducto(productoEntity.getIdProducto());
        producto.setCodigoProducto(productoEntity.getCodigoProducto());
        producto.setFechaCreacionProducto(productoEntity.getFechaCreacionProducto());
        producto.setNombreProducto(productoEntity.getNombreProducto());
        producto.setCategoria(categoriaAdapter.toModel(productoEntity.getCategoria()));
        producto.setStockActualProducto(productoEntity.getStockActualProducto());
        producto.setPrecioCostoProducto(productoEntity.getPrecioCostoProducto());
        producto.setPrecioVentaProducto(productoEntity.getPrecioVentaProducto());
        producto.setDescripcionProducto(productoEntity.getDescripcionProducto());
        producto.setEstadoProducto(productoEntity.getEstadoProducto());

        return producto;
    }
}
