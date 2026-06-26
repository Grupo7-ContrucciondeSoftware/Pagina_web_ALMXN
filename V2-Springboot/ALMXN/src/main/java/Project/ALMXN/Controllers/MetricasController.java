package Project.ALMXN.Controllers;

import Project.ALMXN.Services.CategoriaService;
import Project.ALMXN.Services.MovimientoService;
import Project.ALMXN.Services.ProductoService;
import Project.ALMXN.Services.ProveedorService;
import Project.ALMXN.models.Movimiento;
import Project.ALMXN.models.Producto;
import Project.ALMXN.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gestion/adminMetricas")
public class MetricasController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final ProveedorService proveedorService;
    private final MovimientoService movimientoService;

    public MetricasController(ProductoService productoService,
                              CategoriaService categoriaService,
                              ProveedorService proveedorService,
                              MovimientoService movimientoService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.proveedorService = proveedorService;
        this.movimientoService = movimientoService;
    }

    @GetMapping("")
    public String mostrarMetricas(Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }

        // ── PRODUCTOS ──
        List<Producto> todosProductos = productoService.obtenerTodosLosProductos();

        long totalProductos = todosProductos.size();

        int stockTotal = todosProductos.stream()
                .mapToInt(Producto::getStockActualProducto)
                .sum();

        // ── CATEGORÍAS ──
        long totalCategorias = categoriaService.obtenerTodasLasCategorias().stream()
                .filter(c -> "Activo".equalsIgnoreCase(c.getEstadoCategoria()))
                .count();

        // ── PROVEEDORES ──
        long totalProveedores = proveedorService.obtenerTodosLosProveedores().stream()
                .filter(p -> "Activo".equalsIgnoreCase(p.getEstadoProveedor()))
                .count();

        // ── MOVIMIENTOS ──
        List<Movimiento> todosMovimientos = movimientoService.obtenerTodosLosMovimientos();

        long totalIngresos = todosMovimientos.stream()
                .filter(m -> "Ingreso".equalsIgnoreCase(m.getTipoMovimiento()))
                .count();

        long totalSalidas = todosMovimientos.stream()
                .filter(m -> "Salida".equalsIgnoreCase(m.getTipoMovimiento()))
                .count();

        // ── MÉTRICAS POR SERIES DE TIEMPO (SEMANA DE REFERENCIA) ──
        LocalDate fechaReferencia = todosMovimientos.stream()
                .map(Movimiento::getFechaMovimiento)
                .filter(java.util.Objects::nonNull)
                .map(java.time.LocalDateTime::toLocalDate)
                .max(Comparator.naturalOrder())
                .orElse(LocalDate.now());

        LocalDate inicioSemana = fechaReferencia.with(DayOfWeek.MONDAY);

        List<String> etiquetasDias = new ArrayList<>();
        List<Double> ventasSolesPorDia = new ArrayList<>();
        List<Long> ingresosPorDia = new ArrayList<>();
        List<Long> salidasPorDia = new ArrayList<>();
        List<Long> movimientosPorDia = new ArrayList<>();
        List<Double> ticketPromedioPorDia = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate dia = inicioSemana.plusDays(i);
            etiquetasDias.add(dia.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("es", "ES")));

            List<Movimiento> movimientosDelDia = todosMovimientos.stream()
                    .filter(m -> m.getFechaMovimiento() != null
                            && m.getFechaMovimiento().toLocalDate().isEqual(dia))
                    .collect(Collectors.toList());

            // Ventas en soles del día (movimientos de tipo Salida = ventas)
            double ventasSoles = movimientosDelDia.stream()
                    .filter(m -> "Salida".equalsIgnoreCase(m.getTipoMovimiento()))
                    .mapToDouble(m -> m.getTotalMovimiento() != null ? m.getTotalMovimiento() : 0.0)
                    .sum();
            ventasSolesPorDia.add(ventasSoles);

            // Ingresos registrados del día
            long ingresosDia = movimientosDelDia.stream()
                    .filter(m -> "Ingreso".equalsIgnoreCase(m.getTipoMovimiento()))
                    .count();
            ingresosPorDia.add(ingresosDia);

            // Salidas registradas del día
            long salidasDia = movimientosDelDia.stream()
                    .filter(m -> "Salida".equalsIgnoreCase(m.getTipoMovimiento()))
                    .count();
            salidasPorDia.add(salidasDia);

            // Total de movimientos del día
            movimientosPorDia.add((long) movimientosDelDia.size());

            // Ticket promedio del día (promedio del total por movimiento)
            double ticketPromedio = movimientosDelDia.isEmpty() ? 0.0 :
                    movimientosDelDia.stream()
                    .mapToDouble(m -> m.getTotalMovimiento() != null ? m.getTotalMovimiento() : 0.0)
                    .average()
                    .orElse(0.0);
            ticketPromedioPorDia.add(ticketPromedio);
        }

        // ── MÉTRICA PUNTUAL: ventas en soles del día de referencia ──
        int indiceReferencia = (int) (fechaReferencia.toEpochDay() - inicioSemana.toEpochDay());
        double ventasSolesHoy = ventasSolesPorDia.get(indiceReferencia);

        // ── MODEL ──
        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("stockTotal", stockTotal);
        model.addAttribute("totalCategorias", totalCategorias);
        model.addAttribute("totalProveedores", totalProveedores);
        model.addAttribute("totalIngresos", totalIngresos);
        model.addAttribute("totalSalidas", totalSalidas);
        model.addAttribute("paginaActiva", "gestion");

        // Series de tiempo (gráfico semanal)
        model.addAttribute("etiquetasDias", etiquetasDias);
        model.addAttribute("ventasSolesPorDia", ventasSolesPorDia);
        model.addAttribute("ingresosPorDia", ingresosPorDia);
        model.addAttribute("salidasPorDia", salidasPorDia);
        model.addAttribute("movimientosPorDia", movimientosPorDia);
        model.addAttribute("ticketPromedioPorDia", ticketPromedioPorDia);

        // Métrica puntual del día
        model.addAttribute("ventasSolesHoy", ventasSolesHoy);

        return "gestion/adminMetricas";
    }
}