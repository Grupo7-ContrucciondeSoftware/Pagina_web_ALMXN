package Project.ALMXN.Services;

import Project.ALMXN.Repository.ProveedorRepository;
import Project.ALMXN.adapters.ProveedorAdapter;
import Project.ALMXN.entitys.ProveedorEntity;
import Project.ALMXN.models.Proveedor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorAdapter proveedorAdapter;

    public ProveedorService(ProveedorRepository proveedorRepository, ProveedorAdapter proveedorAdapter) {
        this.proveedorRepository = proveedorRepository;
        this.proveedorAdapter = proveedorAdapter;
    }

    public List<Proveedor> obtenerTodosLosProveedores() {
        return filtrarProveedor(null, null, null, "Activo");
    }

    @Transactional
    public Proveedor guardarProveedor(Proveedor proveedor) {
        String ruc         = proveedor.getRucProveedor();
        String razonSocial = proveedor.getRazonSocialProveedor();
        String telefono    = proveedor.getTelefonoProveedor();
        String correo      = proveedor.getCorreoProveedor();
        Long   id          = proveedor.getIdProveedor();
        boolean esNuevo    = (id == null || id == 0);

        // Validación de RUC (formato SUNAT)
        if (ruc == null || !ruc.matches("^(10|15|20)\\d{9}$")) {
            throw new IllegalArgumentException("El RUC debe ser un número de 11 dígitos que comience con 10, 15 o 20.");
        }

        // Unicidad de RUC
        proveedorRepository.findByRucProveedor(ruc).ifPresent(e -> {
            if (esNuevo || !e.getIdProveedor().equals(id)) {
                throw new IllegalArgumentException("Ya existe un proveedor registrado con el RUC: " + ruc);
            }
        });

        // Unicidad de Razón Social
        proveedorRepository.findByRazonSocialProveedor(razonSocial).ifPresent(e -> {
            if (esNuevo || !e.getIdProveedor().equals(id)) {
                throw new IllegalArgumentException("Ya existe un proveedor registrado con la razón social: " + razonSocial);
            }
        });

        // Unicidad de Teléfono
        proveedorRepository.findByTelefonoProveedor(telefono).ifPresent(e -> {
            if (esNuevo || !e.getIdProveedor().equals(id)) {
                throw new IllegalArgumentException("Ya existe un proveedor registrado con el teléfono: " + telefono);
            }
        });

        // Unicidad de Correo
        proveedorRepository.findByCorreoProveedor(correo).ifPresent(e -> {
            if (esNuevo || !e.getIdProveedor().equals(id)) {
                throw new IllegalArgumentException("Ya existe un proveedor registrado con el correo: " + correo);
            }
        });

        ProveedorEntity entity;

        if (esNuevo) {
            entity = proveedorAdapter.toEntity(proveedor);
            entity.setEstadoProveedor("Activo");
        } else {
            entity = proveedorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
            entity.setRucProveedor(ruc);
            entity.setRazonSocialProveedor(razonSocial);
            entity.setTelefonoProveedor(telefono);
            entity.setCorreoProveedor(correo);
        }

        ProveedorEntity savedEntity = proveedorRepository.save(entity);
        return proveedorAdapter.toModel(savedEntity);
    }

    public Proveedor buscarProveedorPorId(Long idProveedor) {
        ProveedorEntity entity = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + idProveedor));
        return proveedorAdapter.toModel(entity);
    }

    @Transactional
    public void eliminarProveedor(Long idProveedor) {
        ProveedorEntity entity = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + idProveedor));
        entity.setEstadoProveedor("Inactivo");
        proveedorRepository.save(entity);
    }

    @Transactional
    public void activarProveedor(Long idProveedor) {
        ProveedorEntity entity = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + idProveedor));
        entity.setEstadoProveedor("Activo");
        proveedorRepository.save(entity);
    }

    public List<Proveedor> filtrarProveedor(String razonSocial, String ruc, Integer telefono, String estadoFiltro) {

        // 1. Traemos todos los proveedores de la base de datos
        List<ProveedorEntity> todos = proveedorRepository.findAll();

        // 2. Lista donde guardaremos los que pasen los filtros
        List<ProveedorEntity> resultado = new ArrayList<>();

        // 3. Recorremos uno por uno y aplicamos los filtros
        for (ProveedorEntity proveedor : todos) {

            // Filtro por razón social: verifica que contenga el texto
            if (razonSocial != null && !razonSocial.trim().isEmpty()) {
                boolean contieneRazonSocial = proveedor.getRazonSocialProveedor()
                        .toLowerCase()
                        .contains(razonSocial.trim().toLowerCase());
                if (!contieneRazonSocial) {
                    continue;
                }
            }

            // Filtro por RUC: verifica que contenga el texto
            if (ruc != null && !ruc.trim().isEmpty()) {
                boolean contieneRuc = proveedor.getRucProveedor()
                        .contains(ruc.trim());
                if (!contieneRuc) {
                    continue;
                }
            }

            // Filtro por teléfono: verifica que contenga el número
            if (telefono != null && telefono != 0) {
                boolean contieneTelefono = proveedor.getTelefonoProveedor()
                        .contains(telefono.toString());
                if (!contieneTelefono) {
                    continue;
                }
            }

            // Filtro por estado
            if (estadoFiltro == null || estadoFiltro.trim().isEmpty()) {
                // Sin estado → solo "Activo"
                if (!proveedor.getEstadoProveedor().equals("Activo")) {
                    continue;
                }
            } else if (!estadoFiltro.equalsIgnoreCase("Todos")) {
                // Estado específico → filtramos exacto
                if (!proveedor.getEstadoProveedor().equalsIgnoreCase(estadoFiltro)) {
                    continue;
                }
            }

            resultado.add(proveedor);
        }

        // 4. Convertimos las entidades a modelos y retornamos
        List<Proveedor> proveedores = new ArrayList<>();
        for (ProveedorEntity entity : resultado) {
            proveedores.add(proveedorAdapter.toModel(entity));
        }
        return proveedores;
    }
}