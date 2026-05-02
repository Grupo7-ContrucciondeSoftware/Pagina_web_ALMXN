package Project.ALMXN.Controllers;

import Project.ALMXN.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import Project.ALMXN.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/gestion/adminUsuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String mostrarAdminUsuarios(HttpSession session, Model model){
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodosLosUsuarios());
        model.addAttribute("paginaActiva", "gestion");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        return "gestion/adminUsuarios";
    }

    @PostMapping("/guardar")
    public String guardarNuevoUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "redirect:/gestion/adminUsuarios";
    }

    @GetMapping("/editar")
    public String mostrarEditar(@RequestParam("id") int idUsuario, Model model) {
        Usuario usuarioExistente = usuarioService.buscarUsuarioPorId(idUsuario);
        model.addAttribute("paginaActiva", "gestion");
        model.addAttribute("usuario", usuarioExistente);
        return "gestion/editar/editarUsuario";
    }

    @PostMapping("/actualizar")
    public String procesarActualizacion(@ModelAttribute Usuario usuarioModificado) {
        usuarioService.actualizarUsuario(usuarioModificado);
        return "redirect:/gestion/adminUsuarios";
    }

}
