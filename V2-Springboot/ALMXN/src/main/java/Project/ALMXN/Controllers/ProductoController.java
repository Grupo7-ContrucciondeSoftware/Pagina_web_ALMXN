package Project.ALMXN.Controllers;

import Project.ALMXN.Services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/adminProductos")
public class ProductoController {

    private final ProductoService ProductoService;

    public ProductoController(ProductoService ProductoService) {
        this.ProductoService = ProductoService;
    }

    @GetMapping("")
    public String mostrarAdminProductos(Model model) {
        model.addAttribute("listaProductos", ProductoService.obtenerTodosLosProductos());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminProductos";
    }
}