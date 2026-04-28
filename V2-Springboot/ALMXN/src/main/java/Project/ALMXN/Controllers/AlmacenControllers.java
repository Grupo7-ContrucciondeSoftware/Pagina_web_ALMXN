package Project.ALMXN.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlmacenControllers {

    @GetMapping("/login")
    public String mostrarPaginaLogin(Model model){
        model.addAttribute("paginaActiva", "login");
        return "login";
    }

    @GetMapping("/main")
    public String mostrarPaginaMain(Model model) {
        model.addAttribute("paginaActiva", "inicio");
        return "main";
    }

    @GetMapping("/gestion")
    public String mostrarPaginaGestion(Model model) {
        model.addAttribute("paginaActiva", "gestion");
        return "gestion";
    }

    @GetMapping("/gestion/adminMovimientos")
    public String mostrarPaginaAdminMovimientos(Model model) {
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminMovimientos";
    }

    @GetMapping("/contacto")
    public String mostrarPaginaContacto(Model model) {
        model.addAttribute("paginaActiva", "contacto");
        return "contacto";
    }

    @GetMapping("/publicidad")
    public String mostrarPaginaPublicidad(Model model) {
        model.addAttribute("paginaActiva", "publicidad");
        return "publicidad";
    }

}
