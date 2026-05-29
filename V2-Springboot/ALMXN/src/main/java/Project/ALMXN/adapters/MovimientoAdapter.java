package Project.ALMXN.adapters;

import Project.ALMXN.entitys.MovimientoEntity;
import Project.ALMXN.entitys.ProveedorEntity;
import Project.ALMXN.entitys.UsuarioEntity;
import Project.ALMXN.models.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class MovimientoAdapter {

    private UsuarioAdapter usuarioAdapter;
    private ProveedorAdapter proveedorAdapter;

    public MovimientoAdapter(UsuarioAdapter usuarioAdapter, ProveedorAdapter proveedorAdapter){
        this.usuarioAdapter = usuarioAdapter;
        this.proveedorAdapter = proveedorAdapter;
    }

    public MovimientoEntity toEntity(Movimiento movimiento, UsuarioEntity usuarioEntity, ProveedorEntity proveedorEntity){
        if(movimiento == null){
            return null;
        }

        MovimientoEntity movimientoEntity = new MovimientoEntity();

        movimientoEntity.setIdMovimiento(movimiento.getIdMovimiento());
        movimientoEntity.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoEntity.setFechaMovimiento(movimiento.getFechaMovimiento());
        movimientoEntity.setMotivoMovimiento(movimiento.getMotivoMovimiento());
        movimientoEntity.setDestinoMovimiento(movimiento.getDestinoMovimiento());
        movimientoEntity.setObservacionesMovimiento(movimiento.getObservacionesMovimiento());
        movimientoEntity.setUsuario(usuarioEntity);
        movimientoEntity.setProveedor(proveedorEntity);
        movimientoEntity.setTotalMovimiento(movimiento.getTotalMovimiento());

        return movimientoEntity;

    }

    public Movimiento toModel(MovimientoEntity movimientoEntity){
        if(movimientoEntity == null){
            return null;
        }

        Movimiento movimiento = new Movimiento();

        movimiento.setIdMovimiento(movimientoEntity.getIdMovimiento());
        movimiento.setTipoMovimiento(movimientoEntity.getTipoMovimiento());
        movimiento.setFechaMovimiento(movimientoEntity.getFechaMovimiento());
        movimiento.setMotivoMovimiento(movimientoEntity.getMotivoMovimiento());
        movimiento.setDestinoMovimiento(movimientoEntity.getDestinoMovimiento());
        movimiento.setObservacionesMovimiento(movimientoEntity.getObservacionesMovimiento());
        movimiento.setUsuario(usuarioAdapter.toModel(movimientoEntity.getUsuario()));
        movimiento.setProveedor(proveedorAdapter.toModel(movimientoEntity.getProveedor()));
        movimiento.setTotalMovimiento(movimientoEntity.getTotalMovimiento());

        return movimiento;
    }

}
