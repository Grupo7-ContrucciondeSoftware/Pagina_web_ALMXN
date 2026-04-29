package Project.ALMXN.Repository;

import java.util.List;
import Project.ALMXN.models.Proveedor;

public interface ProveedorDAO {

    public List<Proveedor> listaProveedores();

    public void guardarProveedor(Proveedor proveedor);

}
