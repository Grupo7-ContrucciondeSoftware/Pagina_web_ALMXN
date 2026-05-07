package Project.ALMXN.Repository;

import java.util.List;
import Project.ALMXN.models.Proveedor;

public interface ProveedorDAO {

    List<Proveedor> listaProveedores();

    void guardarProveedor(Proveedor proveedor);

    Proveedor buscarProveedorPorId(int idProveedor);

    void actualizarProveedor(Proveedor proveedor);

    void eliminarProveedor(int idProveedor);

    void activarProveedor(int idProveedor);

    List<Proveedor> filtrarProveedor(String razonSocial, String ruc, Integer telefono, String estadoFiltro);

}
