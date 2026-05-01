package Project.ALMXN.Repository;

import Project.ALMXN.models.DetalleMovimiento;
import java.util.List;

public interface DetalleMovimientoDAO {

    List<DetalleMovimiento> buscarPorIdMovimiento(int idMovimiento);

    void guardarDetalleMovimiento(DetalleMovimiento detalleMovimiento);

}
