package Project.ALMXN.Services;

import Project.ALMXN.Repository.UsuarioRepository;
import Project.ALMXN.adapters.UsuarioAdapter;
import Project.ALMXN.entitys.ProductoEntity;
import Project.ALMXN.entitys.UsuarioEntity;
import Project.ALMXN.models.Usuario;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioAdapter usuarioAdapter;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioAdapter usuarioAdapter) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioAdapter = usuarioAdapter;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<UsuarioEntity> entities = usuarioRepository.findAll();
               return entities.stream()
                       .map(e -> usuarioAdapter.toModel(e))
                       .collect(Collectors.toList());
    }

    public Usuario guardarUsuario(Usuario usuario) {

        UsuarioEntity entity;

        if(usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0){
            entity = usuarioAdapter.toEntity(usuario);
            entity.setEstadoUsuario("Activo");
        } else {
            entity = usuarioRepository.findById(usuario.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuario.getIdUsuario()));
            entity.setNombres(usuario.getNombres());
            entity.setApellidos(usuario.getApellidos());
            entity.setCorreo(usuario.getCorreo());
            entity.setRol(usuario.getRol());
        }

        UsuarioEntity savedEntity = usuarioRepository.save(entity);

        return usuarioAdapter.toModel(savedEntity);
    }

    public Usuario buscarUsuarioPorId(Long idUsuario) {
        UsuarioEntity entity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + idUsuario));
        return usuarioAdapter.toModel(entity);
    }

    public void eliminarUsuario(Long idUsuario){
        UsuarioEntity entity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + idUsuario));
        entity.setEstadoUsuario("Inactivo");
    }

    public void activarUsuario(Long idUsuario){
        UsuarioEntity entity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + idUsuario));
        entity.setEstadoUsuario("Activo");
    }

    public Usuario validarLogin(String correo, String contraseña) {
        Optional<UsuarioEntity> entityOptional = usuarioRepository
                .findByCorreoAndContraseñaAndEstado(correo, contraseña, "Activo");

        return entityOptional
                .map(entity -> usuarioAdapter.toModel(entity))
                .orElse(null);
    }

    public List<Usuario> filtrarUsuario(String nombres, String rol, String estado, String fechaMin, String fechaMax){

        Specification<UsuarioEntity> spec = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (nombres != null && !nombres.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombres")),
                        "%" + nombres.trim().toLowerCase() + "%"
                ));
            }

            if (rol != null && !rol.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("rol")),
                        "%" + rol.trim().toLowerCase() + "%"
                ));
            }

            // Rango de Fechas (Ajusta 'fechaCreacionProducto' y los parseos según tu entidad)
            if (fechaMin != null && !fechaMin.isEmpty()) {
                LocalDate inicio = LocalDate.parse(fechaMin);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fechaCreacionProducto"), inicio));
            }
            if (fechaMax != null && !fechaMax.isEmpty()) {
                LocalDate fin = LocalDate.parse(fechaMax);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fechaCreacionProducto"), fin));
            }

            // Filtro por Estado (Igualdad exacta, ej: 'Activo')
            if (estado != null && !estado.isEmpty() && !estado.equalsIgnoreCase("Todos")) {
                predicates.add(criteriaBuilder.equal(root.get("estadoProducto"), estado));
            }

            // Unimos todas las condiciones con un AND lógico
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        // 4. Ejecutamos la búsqueda dinámica nativa de JPA
        List<UsuarioEntity> entities = usuarioRepository.findAll(spec);

        // 5. Convertimos los resultados a tus modelos de dominio
        return entities.stream()
                .map(e -> usuarioAdapter.toModel(e))
                .collect(Collectors.toList());
    }
}