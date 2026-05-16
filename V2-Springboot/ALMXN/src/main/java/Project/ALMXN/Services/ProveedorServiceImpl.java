package Project.ALMXN.Services;

import Project.ALMXN.models.Proveedor;
import Project.ALMXN.Repository.ProveedorDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorDAO proveedorDAO;

    public ProveedorServiceImpl(ProveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    @Override
    public List<Proveedor> obtenerTodosLosProveedores() {
        // return proveedorDAO.listaProveedores(); //F3

        // List<Proveedor> lista = new ArrayList<>(); //F2
        // lista.addAll(proveedorDAO.listaProveedores()); //F2
        // return lista; //F2

        return new ArrayList<>();
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        // if(proveedor.getIdProveedor() == null){ //F3
        // proveedor.setEstadoProveedor("Activo"); //F3
        // proveedorDAO.guardarProveedor(proveedor); //F3
        // } else { //F3
        // proveedorDAO.actualizarProveedor(proveedor); //F3
        // } //F3
        // return proveedor; //F3

        Proveedor proveedor_resp = new Proveedor();
        // proveedor_resp.setRucProveedor(proveedor.getRucProveedor());
        // proveedor_resp.setRazonSocialProveedor(proveedor.getRazonSocialProveedor());
        // //F2
        // proveedor_resp.setCorreoProveedor(proveedor.getCorreoProveedor()); //F2
        // proveedor_resp.setEstadoProveedor("Activo"); //F2
        return proveedor_resp;
    }

    @Override
    public Proveedor actualizarProveedor(Proveedor proveedor) {
        // proveedorDAO.actualizarProveedor(proveedor); //F3
        // return proveedor; //F3

        Proveedor proveedor_resp = new Proveedor();
        // proveedor_resp.setIdProveedor(proveedor.getIdProveedor()); //F2
        // proveedor_resp.setRucProveedor(proveedor.getRucProveedor()); //F2
        // proveedor_resp.setRazonSocialProveedor(proveedor.getRazonSocialProveedor());
        // //F2
        // proveedor_resp.setCorreoProveedor(proveedor.getCorreoProveedor()); //F2
        return proveedor_resp;
    }

    @Override
    public Proveedor buscarProveedorPorId(int idProveedor) {
        return proveedorDAO.buscarProveedorPorId(idProveedor);
    }

    @Override
    public void eliminarProveedor(int idProveedor) {
        // proveedorDAO.eliminarProveedor(idProveedor); //F3
        // System.out.println("Proveedor " + idProveedor + " desactivada"); //F2
    }

    @Override
    public void activarProveedor(int idProveedor) {
        proveedorDAO.activarProveedor(idProveedor);
    }

    @Override
    public List<Proveedor> filtrarProveedor(String razonSocial, String ruc, Integer telefono, String estadoFiltro) {
        return proveedorDAO.filtrarProveedor(razonSocial, ruc, telefono, estadoFiltro);
    }

}