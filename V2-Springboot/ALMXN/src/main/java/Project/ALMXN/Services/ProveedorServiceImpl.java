package Project.ALMXN.Services;

import Project.ALMXN.models.Proveedor;
import Project.ALMXN.Repository.ProveedorDAO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService{

    private final ProveedorDAO ProveedorDAO;

    public ProveedorServiceImpl(ProveedorDAO ProveedorDAO){
        this.ProveedorDAO = ProveedorDAO;
    }

    @Override
    public List<Proveedor> obtenerTodosLosProveedores(){
        return ProveedorDAO.listaProveedores();
    }

    @Override
    public void guardarProveedor(Proveedor proveedor) {
        ProveedorDAO.guardarProveedor(proveedor);
    }

}
