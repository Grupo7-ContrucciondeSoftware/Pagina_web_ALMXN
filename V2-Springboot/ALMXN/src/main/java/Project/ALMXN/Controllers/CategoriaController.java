package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/adminCategorias")
public class CategoriaController {

    private final CategoriaService CategoriaService;

    public CategoriaController(CategoriaService CategoriaService) { this.CategoriaService = CategoriaService; }

    @GetMapping("")
    public String mostrarAdminCategorias(Model model){

        model.addAttribute("listaCategorias", CategoriaService.obtenerTodasLasCategorias());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminCategorias";
    }
}