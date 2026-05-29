package Project.ALMXN.Services;

import java.util.List;
import java.util.stream.Collectors;

import Project.ALMXN.Repository.DetalleMovimientoRepository;
import Project.ALMXN.adapters.DetalleMovimientoAdapter;
import Project.ALMXN.entitys.DetalleMovimientoEntity;
import Project.ALMXN.models.DetalleMovimiento;
import org.springframework.stereotype.Service;

@Service
public class DetalleMovimientoService {

    private final DetalleMovimientoRepository detalleMovimientoRepository;
    private final DetalleMovimientoAdapter detalleMovimientoAdapter;

    public DetalleMovimientoService(DetalleMovimientoRepository detalleMovimientoRepository, DetalleMovimientoAdapter detalleMovimientoAdapter) {
        this.detalleMovimientoRepository = detalleMovimientoRepository;
        this.detalleMovimientoAdapter = detalleMovimientoAdapter;
    }

    public List<DetalleMovimiento> buscarPorIdMovimiento(int idMovimiento) {
        List<DetalleMovimientoEntity> detalles = detalleMovimientoRepository.findByMovimientoIdMovimiento((long) idMovimiento);

        return detalles.stream()
                .map(e -> detalleMovimientoAdapter.toModel(e))
                .collect(Collectors.toList());
    }

}