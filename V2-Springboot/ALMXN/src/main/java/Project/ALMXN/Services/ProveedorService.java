package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Proveedor;

public interface ProveedorService {

    List<Proveedor> obtenerTodosLosProveedores();

    void guardarProveedor(Proveedor proveedor);

    Proveedor buscarProveedorPorId(int idProveedor);

    void actualizarProveedor(Proveedor proveedor);

}
