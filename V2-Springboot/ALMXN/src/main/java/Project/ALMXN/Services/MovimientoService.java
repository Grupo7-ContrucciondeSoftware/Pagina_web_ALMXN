package Project.ALMXN.Services;

import Project.ALMXN.Repository.*;
import Project.ALMXN.adapters.DetalleMovimientoAdapter;
import Project.ALMXN.adapters.MovimientoAdapter;
import Project.ALMXN.entitys.*;
import Project.ALMXN.models.DetalleMovimiento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Project.ALMXN.models.Movimiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoService {

    private final DetalleMovimientoAdapter detalleMovimientoAdapter;
    private final MovimientoRepository movimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;
    private final MovimientoAdapter movimientoAdapter;

    public MovimientoService(DetalleMovimientoAdapter detalleMovimientoAdapter, MovimientoRepository movimientoRepository, ProveedorRepository proveedorRepository,
                             UsuarioRepository usuarioRepository, MovimientoAdapter movimientoAdapter, ProductoRepository productoRepository) {
        this.detalleMovimientoAdapter = detalleMovimientoAdapter;
        this.usuarioRepository = usuarioRepository;
        this.proveedorRepository = proveedorRepository;
        this.movimientoRepository = movimientoRepository;
        this.movimientoAdapter = movimientoAdapter;
        this.productoRepository = productoRepository;
    }

    public List<Movimiento> obtenerTodosLosMovimientos() {
        List<MovimientoEntity> entities = movimientoRepository.findAll();

        List<Movimiento> movimientos = new ArrayList<>();
        for (MovimientoEntity e : entities) {
            movimientos.add(movimientoAdapter.toModel(e));
        }
        return movimientos;
    }

    @Transactional
    public void guardarMovimiento(Movimiento movimiento, List<DetalleMovimiento> detalleMovimiento) {
        UsuarioEntity usuario = usuarioRepository.findById((long) movimiento.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + movimiento.getUsuario().getIdUsuario()));

        ProveedorEntity proveedor = null;
        if (movimiento.getProveedor() != null && movimiento.getProveedor().getIdProveedor() != null) {
            proveedor = proveedorRepository.findById((long) movimiento.getProveedor().getIdProveedor())
                    .orElse(null);
        }

        MovimientoEntity movimientoEntity = movimientoAdapter.toEntity(movimiento, usuario, proveedor);
        movimientoEntity.setFechaMovimiento(LocalDateTime.now());

        double totalCalculado = 0.0;

        for (DetalleMovimiento detalle : detalleMovimiento) {
            ProductoEntity producto = productoRepository.findById((long) detalle.getProducto().getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalle.getProducto().getIdProducto()));

            double subtotal = detalle.getCantidadDetalleMovimiento() * detalle.getPrecioUnitarioDetalleMovimiento();
            detalle.setSubtotalDetalleMovimiento(subtotal);
            totalCalculado += subtotal;

            int cantidadAjuste = detalle.getCantidadDetalleMovimiento();
            if (movimiento.getTipoMovimiento().equalsIgnoreCase("Salida")) {
                cantidadAjuste = cantidadAjuste * -1;
            }

            int nuevoStock = producto.getStockActualProducto() + cantidadAjuste;
            producto.setStockActualProducto(nuevoStock);

            DetalleMovimientoEntity detalleMovimientoEntity = detalleMovimientoAdapter.toEntity(detalle, producto, movimientoEntity);
            movimientoEntity.getDetalles().add(detalleMovimientoEntity);
        }

        movimientoEntity.setTotalMovimiento(totalCalculado);
        movimiento.setTotalMovimiento(totalCalculado);
        movimientoRepository.save(movimientoEntity);
    }

    public List<Movimiento> filtrarMovimientos(String tipo, Integer idProveedor, Integer idUsuario, String fechaMin, String fechaMax) {

        // 1. Traemos todos los movimientos de la base de datos
        List<MovimientoEntity> todos = movimientoRepository.findAll();

        // 2. Lista donde guardaremos los que pasen los filtros
        List<MovimientoEntity> resultado = new ArrayList<>();

        // 3. Recorremos uno por uno y aplicamos los filtros
        for (MovimientoEntity movimiento : todos) {

            // Filtro por tipo: verifica que contenga el texto
            if (tipo != null && !tipo.trim().isEmpty()) {
                boolean contieneTipo = movimiento.getTipoMovimiento()
                        .toLowerCase()
                        .contains(tipo.trim().toLowerCase());
                if (!contieneTipo) {
                    continue;
                }
            }

            // Filtro por proveedor: verifica que coincida el ID
            if (idProveedor != null && idProveedor != 0) {
                if (movimiento.getProveedor() == null ||
                        !movimiento.getProveedor().getIdProveedor().equals(Long.valueOf(idProveedor))) {
                    continue;
                }
            }

            // Filtro por usuario: verifica que coincida el ID
            if (idUsuario != null && idUsuario != 0) {
                if (movimiento.getUsuario() == null ||
                        !movimiento.getUsuario().getIdUsuario().equals(Long.valueOf(idUsuario))) {
                    continue;
                }
            }

            // Filtro por fecha mínima: la fecha del movimiento debe ser >= fechaMin
            if (fechaMin != null && !fechaMin.isEmpty()) {
                LocalDateTime inicio = LocalDate.parse(fechaMin).atStartOfDay();
                if (movimiento.getFechaMovimiento().isBefore(inicio)) {
                    continue;
                }
            }

            // Filtro por fecha máxima: la fecha del movimiento debe ser <= fechaMax
            if (fechaMax != null && !fechaMax.isEmpty()) {
                LocalDateTime fin = LocalDate.parse(fechaMax).atTime(23, 59, 59, 999999999);
                if (movimiento.getFechaMovimiento().isAfter(fin)) {
                    continue;
                }
            }

            resultado.add(movimiento);
        }

        // 4. Convertimos las entidades a modelos y retornamos
        List<Movimiento> movimientos = new ArrayList<>();
        for (MovimientoEntity entity : resultado) {
            movimientos.add(movimientoAdapter.toModel(entity));
        }
        return movimientos;
    }
}