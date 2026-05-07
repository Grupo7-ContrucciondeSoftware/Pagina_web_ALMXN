package Project.ALMXN.Controllers;

import Project.ALMXN.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import Project.ALMXN.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/gestion/adminUsuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String mostrarAdminUsuarios(
            @RequestParam(value= "nombre", required = false) String nombresFiltro,
            @RequestParam(value= "rol", required = false) String rolFiltro,
            @RequestParam(value= "estado", required = false) String estadoFiltro,
            @RequestParam(value = "fechaMin", required = false) String fechaMinFiltro,
            @RequestParam(value = "fechaMax", required = false) String fechaMaxFiltro,
            HttpSession session, Model model){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Usuario> usuarioFiltrado;

        boolean hayFiltro = (nombresFiltro != null && !nombresFiltro.isEmpty()) ||
                (rolFiltro != null && !rolFiltro.isEmpty()) || (estadoFiltro != null && !estadoFiltro.isEmpty()) ||
                (fechaMinFiltro != null && !fechaMinFiltro.isEmpty()) ||
                (fechaMaxFiltro != null && !fechaMaxFiltro.isEmpty());
        if(hayFiltro){
            usuarioFiltrado = usuarioService.filtrarUsuario(nombresFiltro, rolFiltro, estadoFiltro, fechaMinFiltro, fechaMaxFiltro);
        } else {
            usuarioFiltrado = usuarioService.obtenerTodosLosUsuarios();
        }

        model.addAttribute("listaUsuarios", usuarioFiltrado);
        model.addAttribute("paginaActiva", "gestion");

        return "gestion/adminUsuarios";
    }

    @PostMapping("/guardar")
    public String guardarNuevoUsuario(@ModelAttribute Usuario usuario, HttpSession session){
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user == null) {
            return "redirect:/login";
        }
        usuarioService.guardarUsuario(usuario);
        return "redirect:/gestion/adminUsuarios";
    }

    @GetMapping("/editar")
    public String mostrarEditar(@RequestParam("id") int idUsuario, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        Usuario usuarioExistente = usuarioService.buscarUsuarioPorId(idUsuario);
        model.addAttribute("paginaActiva", "gestion");
        model.addAttribute("usuario", usuarioExistente);
        return "gestion/editar/editarUsuario";
    }

    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("idUsuario") Integer idUsuario, HttpSession session){
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null || !usuarioLogueado.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        if (usuarioLogueado.getIdUsuario().equals(idUsuario)) {
            return "redirect:/gestion/adminUsuarios";
        }

        usuarioService.eliminarUsuario(idUsuario);
        return "redirect:/gestion/adminUsuarios";
    }

    @PostMapping("/activar")
    public String activarProducto(@RequestParam("idUsuario") Integer idUsuario, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        usuarioService.activarUsuario(idUsuario);
        return "redirect:/gestion/adminUsuarios";
    }

}
