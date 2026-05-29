package Project.ALMXN.Services;

import Project.ALMXN.Repository.*;
import Project.ALMXN.adapters.DetalleMovimientoAdapter;
import Project.ALMXN.adapters.MovimientoAdapter;
import Project.ALMXN.entitys.*;
import Project.ALMXN.models.DetalleMovimiento;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Project.ALMXN.models.Movimiento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Movimiento> obtenerTodosLosMovimientos(){
        List<MovimientoEntity> entities = movimientoRepository.findAll();
        return entities.stream()
                .map(e -> movimientoAdapter.toModel(e))
                .collect(Collectors.toList());
    }

    @Transactional
    public void guardarMovimiento(Movimiento movimiento, List<DetalleMovimiento> detalleMovimiento){

        UsuarioEntity usuario = usuarioRepository.findById((long) movimiento.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + movimiento.getUsuario().getIdUsuario()));

        ProveedorEntity proveedor = null;
        if (movimiento.getProveedor() != null && movimiento.getProveedor().getIdProveedor() != null) {
            proveedor = proveedorRepository.findById((long) movimiento.getProveedor().getIdProveedor())
                    .orElse(null);
        }

        MovimientoEntity movimientoEntity = movimientoAdapter.toEntity(movimiento, usuario, proveedor);

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

    public List<Movimiento> filtrarMovimientos(String tipo, Integer idProveedor, Integer idUsuario, String fechaMin, String fechaMax){
        Specification<MovimientoEntity> spec = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (tipo != null && !tipo.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("tipoMovimiento")),
                        "%" + tipo.trim().toLowerCase() + "%"
                ));
            }

            if (idProveedor != null && idProveedor != 0) {
                predicates.add(criteriaBuilder.equal(root.get("proveedor").get("idProveedor"), idProveedor));
            }

            if (idUsuario != null && idUsuario != 0) {
                predicates.add(criteriaBuilder.equal(root.get("usuario").get("idUsuario"), idUsuario));
            }

            if (fechaMin != null && !fechaMin.isEmpty()) {
                LocalDate inicio = LocalDate.parse(fechaMin);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fechaMovimiento"), inicio));
            }
            if (fechaMax != null && !fechaMax.isEmpty()) {
                LocalDate fin = LocalDate.parse(fechaMax);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fechaMovimiento"), fin));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<MovimientoEntity> entities = movimientoRepository.findAll(spec);

        return entities.stream()
                .map(e -> movimientoAdapter.toModel(e))
                .collect(Collectors.toList());
    }

}
