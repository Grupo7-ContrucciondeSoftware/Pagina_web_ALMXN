package Project.ALMXN.Services;

import Project.ALMXN.models.Producto;
import Project.ALMXN.Repository.ProductoDAO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDAO ProductoDAO;

    public ProductoServiceImpl(ProductoDAO ProductoDAO) {
        this.ProductoDAO = ProductoDAO;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return ProductoDAO.listaProductos();
    }
}