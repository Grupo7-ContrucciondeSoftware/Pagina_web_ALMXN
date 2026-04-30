package Project.ALMXN.Services;

import Project.ALMXN.models.Proveedor;
import Project.ALMXN.Repository.ProveedorDAO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService{

    private final ProveedorDAO proveedorDAO;

    public ProveedorServiceImpl(ProveedorDAO proveedorDAO){
        this.proveedorDAO = proveedorDAO;
    }

    @Override
    public List<Proveedor> obtenerTodosLosProveedores(){
        return proveedorDAO.listaProveedores();
    }

    @Override
    public void guardarProveedor(Proveedor proveedor) {
        proveedorDAO.guardarProveedor(proveedor);
    }

    @Override
    public Proveedor buscarProveedorPorId(int idProveedor){
        return proveedorDAO.buscarProveedorPorId(idProveedor);
    }

    @Override
    public void actualizarProveedor(Proveedor proveedor){
        proveedorDAO.actualizarProveedor(proveedor);
    }

}