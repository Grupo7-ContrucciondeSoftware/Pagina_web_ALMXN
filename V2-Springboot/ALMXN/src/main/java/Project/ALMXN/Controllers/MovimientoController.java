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

    private final MovimientoService movimientoService;
    private final ProveedorService proveedorService;
    private final UsuarioService usuarioService;
    private final DetalleMovimientoService detalleMovimientoService;

    public MovimientoController(MovimientoService movimientoService, ProveedorService proveedorService, UsuarioService usuarioService, DetalleMovimientoService detalleMovimientoService) {
        this.movimientoService = movimientoService;
        this.proveedorService = proveedorService;
        this.usuarioService = usuarioService;
        this.detalleMovimientoService = detalleMovimientoService;
    }

    @GetMapping("")
    public String mostrarAdminMovimientos(Model model){
        model.addAttribute("listaMovimientos", movimientoService.obtenerTodosLosMovimientos());
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodosLosUsuarios());
        model.addAttribute("listaProveedores", proveedorService.obtenerTodosLosProveedores());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminMovimientos";
    }

    @GetMapping("/obtenerDetalles")
    @ResponseBody
    public List<DetalleMovimiento> obtenerDetalles(@RequestParam("id") int idMovimiento) {
        return detalleMovimientoService.buscarPorIdMovimiento(idMovimiento);
    }

}
