package Project.ALMXN.adapters;

import Project.ALMXN.entitys.ProveedorEntity;
import Project.ALMXN.models.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorAdapter {

    public ProveedorEntity toEntity(Proveedor proveedor){
        if(proveedor == null){
            return null;
        }
        ProveedorEntity proveedorEntity = new ProveedorEntity();

        proveedorEntity.setIdProveedor(proveedor.getIdProveedor());
        proveedorEntity.setRucProveedor(proveedor.getRucProveedor());
        proveedorEntity.setRazonSocialProveedor(proveedor.getRazonSocialProveedor());
        proveedorEntity.setTelefonoProveedor(proveedor.getTelefonoProveedor());
        proveedorEntity.setCorreoProveedor(proveedor.getCorreoProveedor());
        proveedorEntity.setEstadoProveedor(proveedor.getEstadoProveedor());

        return proveedorEntity;
    }

    public Proveedor toModel(ProveedorEntity proveedorEntity){
        if(proveedorEntity == null){
            return null;
        }

        Proveedor proveedor = new Proveedor();

        proveedor.setIdProveedor(proveedorEntity.getIdProveedor());
        proveedor.setRucProveedor(proveedorEntity.getRucProveedor());
        proveedor.setRazonSocialProveedor(proveedorEntity.getRazonSocialProveedor());
        proveedor.setTelefonoProveedor(proveedorEntity.getTelefonoProveedor());
        proveedor.setCorreoProveedor(proveedorEntity.getCorreoProveedor());
        proveedor.setEstadoProveedor(proveedorEntity.getEstadoProveedor());

        return proveedor;
    }
}
