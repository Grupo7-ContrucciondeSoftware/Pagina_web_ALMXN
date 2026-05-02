package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.Services.ProductoService;
import Project.ALMXN.models.Producto;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public String mostrarAdminProductos(
            @RequestParam(value = "nombre", required = false) String nombreFiltro,
            @RequestParam(value = "idCategoria", required = false) Integer idCategoria,
            @RequestParam(value = "stockMin", required = false) Integer stockMinFiltro,
            @RequestParam(value = "stockMax", required = false) Integer stockMaxFiltro,
            @RequestParam(value = "precioMin", required = false) Integer precioMinFiltro,
            @RequestParam(value = "precioMax", required = false) Integer precioMaxFiltro,
            @RequestParam(value = "fechaMin", required = false) String fechaMinFiltro,
            @RequestParam(value = "fechaMax", required = false) String fechaMaxFiltro,
            HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Producto> productosFiltrados;

        boolean hayFiltros = (nombreFiltro != null && !nombreFiltro.isEmpty()) ||
                (idCategoria != null) || (stockMinFiltro != null) || (stockMaxFiltro != null) ||
                (precioMinFiltro != null) || (precioMaxFiltro != null) ||
                (fechaMinFiltro != null && !fechaMinFiltro.isEmpty()) ||
                (fechaMaxFiltro != null && !fechaMaxFiltro.isEmpty());

        if (hayFiltros) {
            productosFiltrados = productoService.filtrarProducto(nombreFiltro, idCategoria, stockMinFiltro, stockMaxFiltro, precioMinFiltro, precioMaxFiltro, fechaMinFiltro, fechaMaxFiltro);
        } else {
            productosFiltrados = productoService.obtenerTodosLosProductos();
        }

        model.addAttribute("listaProductos", productosFiltrados);
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
        model.addAttribute("paginaActiva", "gestion");
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
