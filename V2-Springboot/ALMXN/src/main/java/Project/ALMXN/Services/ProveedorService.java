package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Proveedor;

public interface ProveedorService {

    public List<Proveedor> obtenerTodosLosProveedores();

    public void guardarProveedor(Proveedor proveedor);

    public Proveedor buscarProveedorPorId(int idProveedor);

    public void actualizarProveedor(Proveedor proveedor);

}
