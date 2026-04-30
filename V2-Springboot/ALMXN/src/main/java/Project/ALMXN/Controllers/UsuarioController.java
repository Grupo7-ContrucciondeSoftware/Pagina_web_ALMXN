package Project.ALMXN.Controllers;

import Project.ALMXN.Services.UsuarioService;
import org.springframework.ui.Model;
import Project.ALMXN.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/gestion/adminUsuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String mostrarAdminUsuarios(Model model){
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodosLosUsuarios());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminUsuarios";
    }

    @PostMapping("/guardar")
    public String guardarNuevoUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "redirect:/gestion/adminUsuarios";
    }

}
