package Project.ALMXN.Controllers;

import Project.ALMXN.Services.MovimientoService;
import Project.ALMXN.Services.ProveedorService;
import Project.ALMXN.Services.UsuarioService;
import Project.ALMXN.Services.DetalleMovimientoService;
import Project.ALMXN.models.DetalleMovimiento;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/gestion/adminMovimientos")
public class MovimientoController {

    private final MovimientoService MovimientoService;
    private final ProveedorService ProveedorService;
    private final UsuarioService UsuarioService;
    private final DetalleMovimientoService DetalleMovimientoService;

    public MovimientoController(MovimientoService MovimientoService, ProveedorService ProveedorService, UsuarioService UsuarioService, DetalleMovimientoService DetalleMovimientoService) {
        this.MovimientoService = MovimientoService;
        this.ProveedorService = ProveedorService;
        this.UsuarioService = UsuarioService;
        this.DetalleMovimientoService = DetalleMovimientoService;
    }

    @GetMapping("")
    public String mostrarAdminMovimientos(Model model){
        model.addAttribute("listaMovimientos", MovimientoService.obtenerTodosLosMovimientos());
        model.addAttribute("listaUsuarios", UsuarioService.obtenerTodosLosUsuarios());
        model.addAttribute("listaProveedores", ProveedorService.obtenerTodosLosProveedores());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminMovimientos";
    }

    @GetMapping("/obtenerDetalles")
    @ResponseBody
    public List<DetalleMovimiento> obtenerDetalles(@RequestParam("id") int idMovimiento) {
        // Al tener @ResponseBody, Spring no busca un archivo JSP,
        // sino que convierte la lista de productos a JSON automáticamente.
        return DetalleMovimientoService.buscarPorIdMovimiento(idMovimiento);
    }

}
