package Project.ALMXN.Controllers;

import Project.ALMXN.Services.ProveedorService;
import Project.ALMXN.Services.UsuarioService;
import Project.ALMXN.Services.MovimientoService;
import Project.ALMXN.Services.DetalleMovimientoService;
import Project.ALMXN.models.DetalleMovimiento;
import Project.ALMXN.models.Movimiento;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public String mostrarAdminMovimientos(
            @RequestParam(value = "tipo", required = false) String tipoFiltro,
            @RequestParam(value = "idUsuario", required = false) Integer idUsuarioFiltro,
            @RequestParam(value = "fechaMin", required = false) String fechaMinFiltro,
            @RequestParam(value = "fechaMax", required = false) String fechaMaxFiltro,
            Model model,
            HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Movimiento> movimientosFiltrados;

        boolean hayFiltros = (tipoFiltro != null && !tipoFiltro.isEmpty()) ||
                (idUsuarioFiltro != null) ||
                (fechaMinFiltro != null && !fechaMinFiltro.isEmpty()) ||
                (fechaMaxFiltro != null && !fechaMaxFiltro.isEmpty());

        if (hayFiltros) {
            movimientosFiltrados = movimientoService.filtrarMovimientos(tipoFiltro, idUsuarioFiltro, fechaMinFiltro, fechaMaxFiltro);
        } else {
            movimientosFiltrados = movimientoService.obtenerTodosLosMovimientos();
        }

        model.addAttribute("listaMovimientos", movimientosFiltrados);
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodosLosUsuarios());
        model.addAttribute("listaProveedores", proveedorService.obtenerTodosLosProveedores());
        model.addAttribute("paginaActiva", "gestion");

        return "gestion/adminMovimientos";
    }

    @GetMapping("/obtenerDetalles")
    @ResponseBody
    public List<DetalleMovimiento> obtenerDetalles(@RequestParam("id") int idMovimiento, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return Collections.emptyList();
        }

        return detalleMovimientoService.buscarPorIdMovimiento(idMovimiento);
    }


    @PostMapping("/registrarMovimiento")
    public String guardarMovimiento(
            @ModelAttribute Movimiento movimiento,
            @RequestParam(value = "idProducto[]", required = false) List<Long> idProductos,
            @RequestParam(value = "cantidad[]", required = false) List<Long> cantidades,
            @RequestParam(value = "precioUnitario[]", required = false) List<Double> precios,
            HttpSession session) {

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        movimiento.setUsuario(usuarioLogueado);

        movimientoService.registrarMovimientoCompleto(movimiento, idProductos, cantidades, precios);

        return "redirect:/gestion/adminMovimientos";
    }

}
