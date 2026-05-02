package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.models.Categoria;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gestion/adminCategorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public String mostrarAdminCategorias(HttpSession session, Model model){

        model.addAttribute("listaCategorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
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