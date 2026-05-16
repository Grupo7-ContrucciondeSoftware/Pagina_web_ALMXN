package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Proveedor;

public interface ProveedorService {

    List<Proveedor> obtenerTodosLosProveedores();

    Proveedor guardarProveedor(Proveedor proveedor);

    Proveedor buscarProveedorPorId(int idProveedor);

    Proveedor actualizarProveedor(Proveedor proveedor);

    void eliminarProveedor(int idProveedor);

    void activarProveedor(int idProveedor);

    List<Proveedor> filtrarProveedor(String razonSocial, String ruc, Integer telefono, String estadoFiltro);

}
