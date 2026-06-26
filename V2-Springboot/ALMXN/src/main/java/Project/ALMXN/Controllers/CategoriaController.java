package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestion/adminCategorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public String mostrarAdminCategorias(
            @RequestParam(value = "nombre", required = false) String nombreFiltro,
            @RequestParam(value = "estado", required = false) String estadoFiltro,
            HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        List<Categoria> categoriasFiltradas;

        boolean hayFiltro = (nombreFiltro != null && !nombreFiltro.trim().isEmpty())
                || (estadoFiltro != null && !estadoFiltro.isEmpty());

        if (hayFiltro) {
            categoriasFiltradas = categoriaService.filtrarCategorias(nombreFiltro, estadoFiltro);
        } else {
            categoriasFiltradas = categoriaService.obtenerTodasLasCategorias();
        }

        model.addAttribute("listaCategorias", categoriasFiltradas);
        model.addAttribute("paginaActiva", "gestion");

        return "/gestion/adminCategorias";
    }

    @PostMapping("/guardar")
    public String guardarNuevaCategoria(@ModelAttribute Categoria categoria, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            categoriaService.guardarCategoria(categoria);
        } catch (IllegalArgumentException e) {
            // RN 02: nombre duplicado → redirige al listado sin guardar nada
            return "redirect:/gestion/adminCategorias";
        }

        return "redirect:/gestion/adminCategorias";
    }

    @GetMapping("/editar")
    public String mostrarEditar(@RequestParam("id") Long idCategoria, Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        // Carga la lista completa para que la tabla no quede vacía
        model.addAttribute("listaCategorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");

        // Categoría a editar con nombre distinto para no colisionar con th:each
        model.addAttribute("categoriaEditar", categoriaService.buscarCategoriaPorId(idCategoria));

        return "/gestion/adminCategorias";
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam("idCategoria") Long idCategoria, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        categoriaService.eliminarCategoria(idCategoria);
        return "redirect:/gestion/adminCategorias";
    }

    @PostMapping("/activar")
    public String activarProducto(@RequestParam("idCategoria") Long idCategoria, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        categoriaService.activarCategoria(idCategoria);

        return "redirect:/gestion/adminCategorias";
    }

}