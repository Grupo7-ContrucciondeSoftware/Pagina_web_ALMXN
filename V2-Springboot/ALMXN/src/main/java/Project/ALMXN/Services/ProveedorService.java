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

        // return proveedorDAO.listaProveedores(); //F3

        // List<Proveedor> lista = new ArrayList<>(); //F2
        // lista.addAll(proveedorDAO.listaProveedores()); //F2
        // return lista; //F2
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

        // if(proveedor.getIdProveedor() == null){ //F3
        // proveedor.setEstadoProveedor("Activo"); //F3
        // proveedorDAO.guardarProveedor(proveedor); //F3
        // } else { //F3
        // proveedorDAO.actualizarProveedor(proveedor); //F3
        // } //F3
        // return proveedor; //F3

        // Proveedor proveedor_resp = new Proveedor();
        // proveedor_resp.setRucProveedor(proveedor.getRucProveedor()); //F2
        // proveedor_resp.setRazonSocialProveedor(proveedor.getRazonSocialProveedor()); //F2
        // proveedor_resp.setCorreoProveedor(proveedor.getCorreoProveedor()); //F2
        // proveedor_resp.setEstadoProveedor("Activo"); //F2
        // return proveedor_resp;
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
        //        proveedorDAO.eliminarProveedor(idProveedor); //F3
        // System.out.println("Proveedor " + idProveedor + " desactivada"); //F2
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
                    filtro.setTelefonoProveedor(ruc.trim());
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