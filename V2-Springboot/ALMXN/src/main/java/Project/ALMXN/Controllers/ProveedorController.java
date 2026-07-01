package Project.ALMXN.Controllers;

import Project.ALMXN.Services.ProveedorService;
import Project.ALMXN.models.Proveedor;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/gestion/adminProveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("")
    public String mostrarAdminProveedores(
            @RequestParam(value = "razonSocial", required = false) String razonSocialFiltro,
            @RequestParam(value = "ruc", required = false) String rucFiltro,
            @RequestParam(value = "telefono", required = false) Integer telefonoFiltro,
            @RequestParam(value = "estado", required = false) String estadoFiltro,
            HttpSession session, Model model){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        List<Proveedor> proveedorFiltrado;

        boolean hayFiltros = (razonSocialFiltro != null && !razonSocialFiltro.isEmpty()) ||
                (rucFiltro != null && !rucFiltro.isEmpty()) ||
                (telefonoFiltro != null) || (estadoFiltro != null && !estadoFiltro.isEmpty());

        if (hayFiltros){
            proveedorFiltrado = proveedorService.filtrarProveedor(razonSocialFiltro, rucFiltro, telefonoFiltro, estadoFiltro);
        } else {
            proveedorFiltrado = proveedorService.obtenerTodosLosProveedores();
        }

        model.addAttribute("listaProveedores", proveedorFiltrado);
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminProveedores";
    }

    @PostMapping("/guardar")
    public String guardarNuevoProveedor(@ModelAttribute Proveedor proveedor, HttpSession session,
                                        RedirectAttributes redirectAttributes){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        try {
            proveedorService.guardarProveedor(proveedor);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorRuc", e.getMessage());
            return "redirect:/gestion/adminProveedores";
        }
        return "redirect:/gestion/adminProveedores";
    }

    @GetMapping("/nueva")
    public String mostrarNueva(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("paginaActiva", "gestion");

        return "gestion/nueva/nuevoProveedor";
    }

    @GetMapping("/editar")
    public String mostarEditar(@RequestParam("id") Long idProveedor, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("proveedor", proveedorService.buscarProveedorPorId(idProveedor));
        model.addAttribute("paginaActiva", "gestion");

        return "gestion/editar/editarProveedor";
    }

    @PostMapping("/eliminar")
    public String eliminarProveedor(@RequestParam("idProveedor") Long idProveedor, HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        proveedorService.eliminarProveedor(idProveedor);
        return "redirect:/gestion/adminProveedores";
    }

    @PostMapping("/activar")
    public String activarProducto(@RequestParam("idProveedor") Long idProveedor, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRol().equals("Admin")) {
            return "redirect:/login";
        }

        proveedorService.activarProveedor(idProveedor);
        return "redirect:/gestion/adminProveedores";
    }

}
