package Project.ALMXN.Controllers;

import Project.ALMXN.Services.ProveedorService;
import Project.ALMXN.models.Proveedor;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/gestion/adminProveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("")
    public String mostrarAdminProveedores(HttpSession session, Model model){
        model.addAttribute("listaProveedores", proveedorService.obtenerTodosLosProveedores());
        model.addAttribute("paginaActiva", "gestion");
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        return "gestion/adminProveedores";
    }

    @PostMapping("/guardar")
    public String guardarNuevoProveedor(@ModelAttribute Proveedor proveedor){
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/gestion/adminProveedores";
    }

    @GetMapping("/editar")
    public String mostarEditar(@RequestParam("id") int idProveedor, Model model){
        Proveedor proveedorExistente = proveedorService.buscarProveedorPorId(idProveedor);
        model.addAttribute("paginaActiva", "gestion");
        model.addAttribute("proveedor", proveedorExistente);
        return "gestion/editar/editarProveedor";
    }

    @PostMapping("/actualizar")
    public String procesarActualizacion(@ModelAttribute Proveedor proveedorModificado){
        proveedorService.actualizarProveedor(proveedorModificado);
        return "redirect:/gestion/adminProveedores";
    }

}
