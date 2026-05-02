package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.Services.CategoriaServiceImpl;
import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
            HttpSession session, Model model){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        List<Categoria> categoriasFiltradas;

        if (nombreFiltro != null && !nombreFiltro.trim().isEmpty()) {
            categoriasFiltradas = categoriaService.filtrarCategorias(nombreFiltro);
        } else {
            categoriasFiltradas = categoriaService.obtenerTodasLasCategorias();
        }

        model.addAttribute("listaCategorias", categoriasFiltradas);
        model.addAttribute("paginaActiva", "gestion");

        return "/gestion/adminCategorias";
    }

    @PostMapping("/guardar")
    public String guardarNuevaCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/gestion/adminCategorias";
    }

    @GetMapping("/editar")
    public String mostrarEditar(@RequestParam("id") int idCategoria, Model model){
        Categoria categoriaExistente = categoriaService.buscarCategoriaPorId(idCategoria);
        model.addAttribute("paginaActiva", "gestion");
        model.addAttribute("categoria", categoriaExistente);
        return "gestion/editar/editarCategoria";
    }

    @PostMapping("/actualizar")
    public String procesarActualizacion(@ModelAttribute Categoria categoriaModificada){
        categoriaService.actualizarCategoria(categoriaModificada);
        return "redirect:/gestion/adminCategorias";
    }

}