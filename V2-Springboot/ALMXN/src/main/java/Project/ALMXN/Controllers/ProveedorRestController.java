/*package Project.ALMXN.Controllers;

import Project.ALMXN.Services.ProveedorService;
import Project.ALMXN.models.Proveedor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorRestController {
    private final ProveedorService proveedorService;

    public ProveedorRestController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    // ==================== CREAR ====================
    @PostMapping("/guardar")
    public Proveedor crear(@RequestBody Proveedor proveedor) {
        return proveedorService.guardarProveedor(proveedor);
    }

    // ==================== LISTAR TODOS ====================
    @GetMapping("/mostrar")
    public List<Proveedor> mostrarProveedores() {
        return proveedorService.obtenerTodosLosProveedores();
    }

    // ==================== ACTUALIZAR ====================
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable("id") int id, @RequestBody Proveedor proveedor) {
        Proveedor existente = proveedorService.buscarProveedorPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        proveedor.setIdProveedor(id);
        Proveedor actualizado = proveedorService.actualizarProveedor(proveedor);
        return ResponseEntity.ok(actualizado);
    }

    // ==================== ELIMINAR (Soft Delete) ====================
    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable("id") int id) {
        Proveedor existente = proveedorService.buscarProveedorPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.ok(Map.of("mensaje", "Proveedor desactivado correctamente"));
    }
}*/
