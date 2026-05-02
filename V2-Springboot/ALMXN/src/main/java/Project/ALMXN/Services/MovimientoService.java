package Project.ALMXN.Services;

import Project.ALMXN.models.Movimiento;
import java.util.List;

public interface MovimientoService {

    List<Movimiento> obtenerTodosLosMovimientos();

    void guardarMovimiento(Movimiento movimiento);

    void registrarMovimientoCompleto(Movimiento movimiento, List<Integer> idProductos, List<Integer> cantidades, List<Double> precios);

    List<Movimiento> filtrarMovimientos(String tipo, Integer idUsuario, String fechaMin, String fechaMax);


}
