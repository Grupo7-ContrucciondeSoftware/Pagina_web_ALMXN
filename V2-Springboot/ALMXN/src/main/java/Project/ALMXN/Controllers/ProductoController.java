package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.Services.ProductoService;
import Project.ALMXN.models.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/adminProductos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public String mostrarAdminProductos(Model model) {
        model.addAttribute("listaProductos", productoService.obtenerTodosLosProductos());
        model.addAttribute("listaCategorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminProductos";
    }

    @PostMapping("/guardar")
    public String guardarNuevoProducto(@ModelAttribute Producto producto){
        productoService.guardarProducto(producto);
        return ("redirect:/gestion/adminProductos");
    }

}