package Project.ALMXN.Controllers;

import Project.ALMXN.Services.ProveedorService;
import Project.ALMXN.models.Proveedor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/gestion/adminProveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("")
    public String mostrarAdminProveedores(Model model){
        model.addAttribute("listaProveedores", proveedorService.obtenerTodosLosProveedores());
        model.addAttribute("paginaActiva", "gestion");
        return "gestion/adminProveedores";
    }

    @PostMapping("/guardar")
    public String guardarNuevoProveedor(@ModelAttribute Proveedor proveedor){
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/gestion/adminProveedores";
    }

}
