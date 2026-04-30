package Project.ALMXN.Services;

import Project.ALMXN.Repository.MovimientoDAO;
import org.springframework.stereotype.Service;
import Project.ALMXN.models.Movimiento;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService{

    private final MovimientoDAO movimientoDAO;

    public MovimientoServiceImpl(MovimientoDAO movimientoDAO){
        this.movimientoDAO = movimientoDAO;
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientos(){
        return movimientoDAO.listaMovimientos();
    }

}
