package Project.ALMXN.Controllers;

import Project.ALMXN.Services.MovimientoService;
import Project.ALMXN.Services.ProveedorService;
import Project.ALMXN.Services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/adminMovimientos")
public class MovimientoController {

    private final MovimientoService MovimientoService;
    private final ProveedorService ProveedorService;
    private final UsuarioService UsuarioService;

    public MovimientoController(MovimientoService MovimientoService, ProveedorService ProveedorService, UsuarioService UsuarioService) {
        this.MovimientoService = MovimientoService;
        this.ProveedorService = ProveedorService;
        this.UsuarioService = UsuarioService;
    }

    @GetMapping("")
    public String mostrarAdminMovimientos(Model model){
        model.addAttribute("listaMovimientos", MovimientoService.obtenerTodosLosMovimientos());
        model.addAttribute("listaUsuarios", UsuarioService.obtenerTodosLosUsuarios());
        model.addAttribute("listaProveedores", ProveedorService.obtenerTodosLosProveedores());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminMovimientos";
    }

}
