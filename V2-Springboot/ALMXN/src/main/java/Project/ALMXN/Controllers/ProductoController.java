package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.Services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/adminProductos")
public class ProductoController {

    private final ProductoService ProductoService;
    private final CategoriaService CategoriaService;

    public ProductoController(ProductoService ProductoService, CategoriaService CategoriaService) {
        this.ProductoService = ProductoService;
        this.CategoriaService = CategoriaService;
    }

    @GetMapping("")
    public String mostrarAdminProductos(Model model) {
        model.addAttribute("listaProductos", ProductoService.obtenerTodosLosProductos());
        model.addAttribute("listaCategorias", CategoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminProductos";
    }
}