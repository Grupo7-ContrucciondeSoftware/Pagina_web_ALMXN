package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.Services.ProductoService;
import Project.ALMXN.models.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "redirect:/gestion/adminProductos";
    }

    @GetMapping("/editar")
    public String mostrarEditar(@RequestParam("id") int idProducto, Model model) {
        Producto productoExistente = productoService.buscarProductoPorId(idProducto);
        model.addAttribute("producto", productoExistente);
        model.addAttribute("listaCategorias", categoriaService.obtenerTodasLasCategorias());
        return "gestion/editar/editarProducto";
    }

    @PostMapping("/actualizar")
    public String procesarActualizacion(@ModelAttribute Producto productoModificado){
        productoService.actualizarProducto(productoModificado);
        return "redirect:/gestion/adminProductos";
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam("idProducto") int idProducto) {
        productoService.eliminarProducto(idProducto);
        return "redirect:/gestion/adminProductos";
    }
}
