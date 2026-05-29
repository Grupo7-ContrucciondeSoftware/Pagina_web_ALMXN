package Project.ALMXN.Controllers;

import Project.ALMXN.Services.UsuarioService;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlmacenControllers {

    private final UsuarioService usuarioService;

    public AlmacenControllers(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarPaginaLogin(Model model) {
        model.addAttribute("paginaActiva", "login");
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String correo,
            @RequestParam("contraseña") String contrasena,
            HttpSession session) {

        Usuario usuario = usuarioService.validarLogin(correo, contrasena);

        if (usuario != null) {
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/main";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/main")
    public String mostrarPaginaMain(HttpSession session, Model model) {
        model.addAttribute("paginaActiva", "inicio");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        return "main";
    }

    @GetMapping("/gestion")
    public String mostrarPaginaGestion(Model model, HttpSession session) {
        model.addAttribute("paginaActiva", "gestion");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        return "gestion";
    }

    @GetMapping("/contacto")
    public String mostrarPaginaContacto(Model model, HttpSession session) {
        model.addAttribute("paginaActiva", "contacto");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        return "contacto";
    }

    @GetMapping("/publicidad")
    public String mostrarPaginaPublicidad(Model model, HttpSession session) {
        model.addAttribute("paginaActiva", "publicidad");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        return "publicidad";
    }
}