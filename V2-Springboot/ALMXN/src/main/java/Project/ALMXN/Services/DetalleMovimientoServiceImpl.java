package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.Repository.DetalleMovimientoDAO;
import Project.ALMXN.models.DetalleMovimiento;
import org.springframework.stereotype.Service;

@Service
public class DetalleMovimientoServiceImpl implements DetalleMovimientoService {

    private final DetalleMovimientoDAO detalleMovimientoDAO;

    public DetalleMovimientoServiceImpl(DetalleMovimientoDAO detalleMovimientoDAO) {
        this.detalleMovimientoDAO = detalleMovimientoDAO;
    }

    @Override
    public List<DetalleMovimiento> buscarPorIdMovimiento(int idMovimiento) {
        return detalleMovimientoDAO.buscarPorIdMovimiento(idMovimiento);
    }

}