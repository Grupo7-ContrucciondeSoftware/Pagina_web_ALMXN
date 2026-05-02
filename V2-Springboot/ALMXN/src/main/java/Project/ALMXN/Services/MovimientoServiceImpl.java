package Project.ALMXN.Services;

import Project.ALMXN.Repository.DetalleMovimientoDAO;
import Project.ALMXN.Repository.MovimientoDAO;
import Project.ALMXN.models.DetalleMovimiento;
import Project.ALMXN.models.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Project.ALMXN.models.Movimiento;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoDAO movimientoDAO;
    private final DetalleMovimientoDAO detalleMovimientoDAO;
    private final ProductoService productoService;

    public MovimientoServiceImpl(MovimientoDAO movimientoDAO,DetalleMovimientoDAO detalleMovimientoDAO, ProductoService productoService){
        this.movimientoDAO = movimientoDAO;
        this.detalleMovimientoDAO = detalleMovimientoDAO;
        this.productoService = productoService;
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientos(){
        return movimientoDAO.listaMovimientos();
    }

    @Override
    public void guardarMovimiento(Movimiento movimiento){
        movimientoDAO.guardarMovimiento(movimiento);
    }

    @Override
    @Transactional
    public void registrarMovimientoCompleto(Movimiento movimiento, List<Integer> idProductos, List<Integer> cantidades, List<Double> precios) {

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
            int idProd = idProductos.get(i);
            int cant = cantidades.get(i);

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

}
