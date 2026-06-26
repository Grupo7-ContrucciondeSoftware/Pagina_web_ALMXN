package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.Services.ProductoService;
import Project.ALMXN.models.Producto;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam(value = "idCategoria", required = false) Long idCategoria,
            @RequestParam(value = "stock", required = false) Integer stockFiltro,
            @RequestParam(value = "precio", required = false) Double precioFiltro,
            @RequestParam(value = "fecha", required = false) String fechaFiltro,
            @RequestParam(value = "estado", required = false) String estadoFiltro,
            HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Producto> productosFiltrados;

        boolean hayFiltros = (nombreFiltro != null && !nombreFiltro.isEmpty()) ||
                (idCategoria != null) || (stockFiltro != null) ||
                (precioFiltro != null) ||
                (fechaFiltro != null && !fechaFiltro.isEmpty()) ||
                (estadoFiltro != null && !estadoFiltro.isEmpty());

        if (hayFiltros) {
            productosFiltrados = productoService.filtrarProducto(nombreFiltro, idCategoria, stockFiltro, precioFiltro, fechaFiltro, estadoFiltro);
        } else {
            productosFiltrados = productoService.obtenerTodosLosProductos();
        }

        model.addAttribute("listaProductos", productosFiltrados);
        model.addAttribute("listaCategorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");

        return "gestion/adminProductos";
    }

    // ── NUEVO ──────────────────────────────────────────────────────────────────
    @GetMapping("/agregar")
    public String mostrarAgregarProducto(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("listaCategorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/agregarProducto";
    }
    // ──────────────────────────────────────────────────────────────────────────

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto, HttpSession session,
                                  RedirectAttributes redirectAttributes) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            productoService.guardarProducto(producto);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorNombre", e.getMessage());
            return "redirect:/gestion/adminProductos";
        }

        return "redirect:/gestion/adminProductos";
    }

    @GetMapping("/editar")
    public String mostrarEditar(@RequestParam("id") Long idProducto, Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        // Carga la lista completa para que la tabla no quede vacía
        model.addAttribute("listaProductos", productoService.obtenerTodosLosProductos());
        model.addAttribute("listaCategorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");

        // Producto a editar con nombre distinto para no colisionar con th:each
        model.addAttribute("productoEditar", productoService.buscarProductoPorId(idProducto));

        return "gestion/adminProductos";
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam("idProducto") Long idProducto, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        productoService.eliminarProducto(idProducto);
        return "redirect:/gestion/adminProductos";
    }

    @PostMapping("/activar")
    public String activarProducto(@RequestParam("idProducto") Long idProducto, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        productoService.activarProducto(idProducto);

        return "redirect:/gestion/adminProductos";
    }
}