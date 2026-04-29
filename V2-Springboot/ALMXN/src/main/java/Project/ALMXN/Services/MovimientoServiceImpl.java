package Project.ALMXN.Services;

import Project.ALMXN.Repository.MovimientoDAO;
import org.springframework.stereotype.Service;
import Project.ALMXN.models.Movimiento;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService{

    private final MovimientoDAO MovimientoDAO;

    public MovimientoServiceImpl(MovimientoDAO MovimientoDAO){
        this.MovimientoDAO = MovimientoDAO;
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientos(){
        return MovimientoDAO.listaMovimientos();
    }

}
