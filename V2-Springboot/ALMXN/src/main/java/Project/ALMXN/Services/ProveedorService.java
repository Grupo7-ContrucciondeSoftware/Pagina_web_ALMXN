package Project.ALMXN.Services;

import Project.ALMXN.Repository.ProveedorRepository;
import Project.ALMXN.adapters.ProveedorAdapter;
import Project.ALMXN.entitys.ProveedorEntity;
import Project.ALMXN.models.Proveedor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorAdapter proveedorAdapter;

    public ProveedorService(ProveedorRepository proveedorRepository, ProveedorAdapter proveedorAdapter) {
        this.proveedorRepository = proveedorRepository;
        this.proveedorAdapter = proveedorAdapter;
    }

    public List<Proveedor> obtenerTodosLosProveedores() {

        List<ProveedorEntity> entities = proveedorRepository.findAll();

        return entities.stream()
                .map(e -> proveedorAdapter.toModel(e))
                .collect(Collectors.toList());
    }

    public Proveedor guardarProveedor(Proveedor proveedor) {

        ProveedorEntity entity = proveedorAdapter.toEntity(proveedor);

        if (proveedor.getIdProveedor() == null || proveedor.getIdProveedor() == 0) {
            entity = proveedorAdapter.toEntity(proveedor);
            entity.setEstadoProveedor("Activo");
        } else {
            entity = proveedorRepository.findById(proveedor.getIdProveedor())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + proveedor.getIdProveedor()));
            entity.setRucProveedor(proveedor.getRucProveedor());
            entity.setRazonSocialProveedor(proveedor.getRazonSocialProveedor());
            entity.setTelefonoProveedor(proveedor.getTelefonoProveedor());
            entity.setCorreoProveedor(proveedor.getCorreoProveedor());

        }
        ProveedorEntity savedEntity = proveedorRepository.save(entity);
        return proveedorAdapter.toModel(savedEntity);

    }

    public Proveedor buscarProveedorPorId(Long idProveedor) {
        ProveedorEntity entity = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + idProveedor));
        return proveedorAdapter.toModel(entity);
    }

    public void eliminarProveedor(Long idProveedor) {
        ProveedorEntity entity = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + idProveedor));
        entity.setEstadoProveedor("Inactivo");
    }

    public void activarProveedor(Long idProveedor) {
        ProveedorEntity entity = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + idProveedor));
        entity.setEstadoProveedor("Activo");
    }

    public List<Proveedor> filtrarProveedor(String razonSocial, String ruc, Integer telefono, String estadoFiltro) {
                ProveedorEntity filtro = new ProveedorEntity();

                if (razonSocial != null && !razonSocial.trim().isEmpty()) {
                    filtro.setRazonSocialProveedor(razonSocial.trim());
                }

                if (ruc != null && !ruc.trim().isEmpty()) {
                    filtro.setRucProveedor(ruc.trim());
                }

                if (telefono != null && telefono != 0) {
                    filtro.setTelefonoProveedor(telefono.toString());
                }

                if (estadoFiltro != null && !estadoFiltro.isEmpty() && !estadoFiltro.equalsIgnoreCase("Todos")) {
                    filtro.setEstadoProveedor(estadoFiltro);
                }

                // 2. Configuramos las reglas del matcher (Ignorar mayúsculas/minúsculas y aplicar el LIKE)
                ExampleMatcher matcher = ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                        .withIgnoreNullValues();

                // 3. Empaquetamos el molde y las reglas
                Example<ProveedorEntity> example = Example.of(filtro, matcher);

                // 4. Ejecutamos la búsqueda dinámica nativa de JPA
                List<ProveedorEntity> entities = proveedorRepository.findAll(example);

                // 5. Convertimos los resultados a tus modelos de dominio
                return entities.stream()
                        .map(e -> proveedorAdapter.toModel(e))
                        .collect(Collectors.toList());
    }

}