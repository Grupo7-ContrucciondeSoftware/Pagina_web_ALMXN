package Project.ALMXN.Controllers;

import Project.ALMXN.Services.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/adminProveedores")
public class ProveedorController {

    private final ProveedorService ProveedorService;

    public ProveedorController(ProveedorService ProveedorService) { this.ProveedorService = ProveedorService; }

    @GetMapping("")
    public String mostrarAdminProveedores(Model model){
        model.addAttribute("listaProveedores", ProveedorService.obtenerTodosLosProveedores());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminProveedores";
    }
}
