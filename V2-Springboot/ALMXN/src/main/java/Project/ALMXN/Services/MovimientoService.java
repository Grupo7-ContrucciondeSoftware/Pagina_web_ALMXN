package Project.ALMXN.Services;

import Project.ALMXN.Repository.DetalleMovimientoDAO;
import Project.ALMXN.Repository.DetalleMovimientoRepository;
import Project.ALMXN.Repository.MovimientoDAO;
import Project.ALMXN.Repository.MovimientoRepository;
import Project.ALMXN.adapters.DetalleMovimientoAdapter;
import Project.ALMXN.adapters.MovimientoAdapter;
import Project.ALMXN.models.DetalleMovimiento;
import Project.ALMXN.models.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Project.ALMXN.models.Movimiento;
import java.util.List;

@Service
public class MovimientoService {

    private final DetalleMovimientoRepository detalleMovimientoRepository;
    private final DetalleMovimientoAdapter detalleMovimientoAdapter;
    private final MovimientoRepository movimientoRepository;
    private final MovimientoAdapter movimientoAdapter;
    private final ProductoService productoService;

    public MovimientoService(DetalleMovimientoRepository detalleMovimientoRepository, DetalleMovimientoAdapter detalleMovimientoAdapter, MovimientoRepository movimientoRepository, MovimientoAdapter movimientoAdapter, ProductoService productoService) {
        this.detalleMovimientoRepository = detalleMovimientoRepository;
        this.detalleMovimientoAdapter = detalleMovimientoAdapter;
        this.movimientoRepository = movimientoRepository;
        this.movimientoAdapter = movimientoAdapter;
        this.productoService = productoService;
    }

    public List<Movimiento> obtenerTodosLosMovimientos(){
        return movimientoDAO.listaMovimientos();
    }

    public void guardarMovimiento(Movimiento movimiento){
        movimientoDAO.guardarMovimiento(movimiento);
    }

    @Transactional
    public void registrarMovimientoCompleto(Movimiento movimiento, List<Long> idProductos, List<Long> cantidades, List<Double> precios) {

        double totalCalculado = 0.0;

        if (idProductos != null && !idProductos.isEmpty()) {
            for (int i = 0; i < idProductos.size(); i++) {
                totalCalculado += (cantidades.get(i) * precios.get(i));
            }
        }

        movimiento.setTotalMovimiento(totalCalculado);

        int nuevoIdMovimiento = movimientoDAO.guardarMovimiento(movimiento);
        if (idProductos == null || idProductos.isEmpty()) return;

        Movimiento movGuardado = new Movimiento();
        movGuardado.setIdMovimiento(nuevoIdMovimiento);

        for (int i = 0; i < idProductos.size(); i++) {
            Long idProd = idProductos.get(i);
            Long cant = cantidades.get(i);

            Producto producto = new Producto();
            producto.setIdProducto(idProductos.get(i));

            DetalleMovimiento detalle = new DetalleMovimiento();

            detalle.setMovimiento(movGuardado);
            detalle.setProducto(producto);
            detalle.setCantidadDetalleMovimiento(cantidades.get(i));
            detalle.setPrecioUnitarioDetalleMovimiento(precios.get(i));
            detalle.setSubtotalDetalleMovimiento(cantidades.get(i) * precios.get(i));

            detalleMovimientoDAO.guardarDetalleMovimiento(detalle);

            int cantidadAjuste = cant;

            if (movimiento.getTipoMovimiento().equalsIgnoreCase("Salida")) {
                cantidadAjuste = cant * -1;
            }
            productoService.actualizarStock(idProd, cantidadAjuste);
        }
    }

    public List<Movimiento> filtrarMovimientos(String tipo, Integer idUsuario, String fechaMin, String fechaMax){
        return movimientoDAO.filtrarMovimientos(tipo, idUsuario, fechaMin, fechaMax);
    }

}
