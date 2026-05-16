package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.models.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {
    private final CategoriaService categoriaService;

    public CategoriaRestController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // ==================== CREAR ====================
    @PostMapping("/guardar")
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    // ==================== LISTAR TODAS ====================
    @GetMapping("/mostrar")
    public List<Categoria> mostrarCategorias() {
        return categoriaService.obtenerTodasLasCategorias();
    }

    // ==================== ACTUALIZAR ====================
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable("id") int id, @RequestBody Categoria categoria) {
        Categoria existente = categoriaService.buscarCategoriaPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        categoria.setIdCategoria(id);
        Categoria actualizada = categoriaService.actualizarCategoria(categoria);
        return ResponseEntity.ok(actualizada);
    }

    // ==================== ELIMINAR (Soft Delete) ====================
    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable("id") int id) {
        Categoria existente = categoriaService.buscarCategoriaPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok(Map.of("mensaje", "Categoría desactivada correctamente"));
    }
}
