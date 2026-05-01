package Project.ALMXN.Controllers;

import Project.ALMXN.Services.ProductoService;
import Project.ALMXN.models.Producto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    private final ProductoService productoService;

    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/buscar")
    public List<Producto> buscarProductos(@RequestParam("q") String query) {
        return productoService.buscarProductosParaMovimiento(query);
    }
}
