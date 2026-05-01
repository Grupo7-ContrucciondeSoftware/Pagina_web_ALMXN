package Project.ALMXN.Repository;

import Project.ALMXN.models.Movimiento;
import java.util.List;

public interface MovimientoDAO {

    List<Movimiento> listaMovimientos();

    int guardarMovimiento(Movimiento movimiento);

}