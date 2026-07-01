package Project.ALMXN.Controllers;

import Project.ALMXN.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import Project.ALMXN.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam(value = "fecha", required = false) String fechaFiltro,
            HttpSession session, Model model){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Usuario> usuarioFiltrado;

        boolean hayFiltro = (nombresFiltro != null && !nombresFiltro.isEmpty()) ||
                (rolFiltro != null && !rolFiltro.isEmpty()) || (estadoFiltro != null && !estadoFiltro.isEmpty()) ||
                (fechaFiltro != null && !fechaFiltro.isEmpty());
        if(hayFiltro){
            usuarioFiltrado = usuarioService.filtrarUsuario(nombresFiltro, rolFiltro, estadoFiltro, fechaFiltro);
        } else {
            usuarioFiltrado = usuarioService.obtenerTodosLosUsuarios();
        }

        model.addAttribute("listaUsuarios", usuarioFiltrado);
        model.addAttribute("paginaActiva", "gestion");

        return "gestion/adminUsuarios";
    }

    @PostMapping("/guardar")
    public String guardarNuevoUsuario(@ModelAttribute Usuario usuario, HttpSession session,
                                      RedirectAttributes redirectAttributes){
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
        if (user == null) {
            return "redirect:/login";
        }
        boolean esNuevo = (usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0);
        try{
            usuarioService.guardarUsuario(usuario);
            return "redirect:/gestion/adminUsuarios";
        }
        catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return esNuevo ? "redirect:/gestion/adminUsuarios/nueva" : "redirect:/gestion/adminUsuarios/editar?id=" + usuario.getIdUsuario();
        }
    }

    @GetMapping("/nueva")
    public String mostrarNueva(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", new Usuario());
        model.addAttribute("paginaActiva", "gestion");

        return "gestion/nueva/nuevoUsuario";
    }

    @GetMapping("/editar")
    public String mostrarEditar(@RequestParam("id") Long idUsuario, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuarioService.buscarUsuarioPorId(idUsuario));
        model.addAttribute("paginaActiva", "gestion");

        return "gestion/editar/editarUsuario";
    }

    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("idUsuario") Long idUsuario, HttpSession session){
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
    public String activarProducto(@RequestParam("idUsuario") Long idUsuario, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        usuarioService.activarUsuario(idUsuario);
        return "redirect:/gestion/adminUsuarios";
    }

}
