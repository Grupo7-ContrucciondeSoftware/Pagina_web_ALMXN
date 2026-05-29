package Project.ALMXN.adapters;

import Project.ALMXN.entitys.DetalleMovimientoEntity;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.entitys.MovimientoEntity;
import Project.ALMXN.models.DetalleMovimiento;
import org.springframework.stereotype.Component;

@Component
public class DetalleMovimientoAdapter {

    private ProductoAdapter productoAdapter;
    private MovimientoAdapter movimientoAdapter;

    public DetalleMovimientoAdapter(ProductoAdapter productoAdapter, MovimientoAdapter movimientoAdapter) {
        this.productoAdapter = productoAdapter;
        this.movimientoAdapter = movimientoAdapter;
    }

    public DetalleMovimientoEntity toEntity(DetalleMovimiento detalleMovimiento, ProductoEntity productoEntity, MovimientoEntity movimientoEntity){
        if(detalleMovimiento == null){
            return null;
        }

        DetalleMovimientoEntity detalleMovimientoEntity = new DetalleMovimientoEntity();

        detalleMovimientoEntity.setIdDetalleMovimiento(detalleMovimiento.getIdDetalleMovimiento());
        detalleMovimientoEntity.setMovimiento(movimientoEntity);
        detalleMovimientoEntity.setProducto(productoEntity);
        detalleMovimientoEntity.setCantidadDetalleMovimiento(detalleMovimiento.getCantidadDetalleMovimiento());
        detalleMovimientoEntity.setPrecioUnitarioDetalleMovimiento(detalleMovimiento.getPrecioUnitarioDetalleMovimiento());
        detalleMovimientoEntity.setSubtotalDetalleMovimiento(detalleMovimiento.getSubtotalDetalleMovimiento());

        return detalleMovimientoEntity;
    }

    public DetalleMovimiento toModel(DetalleMovimientoEntity detalleMovimientoEntity){
        if(detalleMovimientoEntity == null){
            return null;
        }

        DetalleMovimiento detalleMovimiento = new DetalleMovimiento();

        detalleMovimiento.setIdDetalleMovimiento(detalleMovimientoEntity.getIdDetalleMovimiento());
        detalleMovimiento.setMovimiento(movimientoAdapter.toModel(detalleMovimientoEntity.getMovimiento()));
        detalleMovimiento.setProducto(productoAdapter.toModel(detalleMovimientoEntity.getProducto()));
        detalleMovimiento.setCantidadDetalleMovimiento(detalleMovimientoEntity.getCantidadDetalleMovimiento());
        detalleMovimiento.setPrecioUnitarioDetalleMovimiento(detalleMovimientoEntity.getPrecioUnitarioDetalleMovimiento());
        detalleMovimiento.setSubtotalDetalleMovimiento(detalleMovimientoEntity.getSubtotalDetalleMovimiento());

        return detalleMovimiento;
    }


}
