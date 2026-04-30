package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.models.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/adminCategorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public String mostrarAdminCategorias(Model model){

        model.addAttribute("listaCategorias", categoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");
        return "/gestion/adminCategorias";
    }

    @PostMapping("/guardar")
    public String guardarNuevaCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/gestion/adminCategorias";
    }
}