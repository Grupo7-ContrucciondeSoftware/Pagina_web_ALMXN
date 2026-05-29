package Project.ALMXN.Services;

import Project.ALMXN.Repository.UsuarioRepository;
import Project.ALMXN.adapters.UsuarioAdapter;
import Project.ALMXN.entitys.UsuarioEntity;
import Project.ALMXN.models.Usuario;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
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
        return filtrarUsuario(null, null, "Activo", null, null);
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {

        UsuarioEntity entity;

        if(usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0){
            usuario.setEstadoUsuario("Activo");
            entity = usuarioAdapter.toEntity(usuario);
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

    @Transactional
    public void eliminarUsuario(Long idUsuario){
        UsuarioEntity entity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + idUsuario));
        entity.setEstado("Inactivo");
        usuarioRepository.save(entity);
    }

    @Transactional
    public void activarUsuario(Long idUsuario){
        UsuarioEntity entity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + idUsuario));
        entity.setEstado("Activo");
        usuarioRepository.save(entity);
    }

    public Usuario validarLogin(String correo, String contrasena) {
        Optional<UsuarioEntity> entityOptional = usuarioRepository
                .findByCorreoAndContrasenaAndEstado(correo, contrasena, "Activo");

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

            if (fechaMin != null && !fechaMin.isEmpty()) {
                LocalDate inicio = LocalDate.parse(fechaMin);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fechaCreacion"), inicio));
            }
            if (fechaMax != null && !fechaMax.isEmpty()) {
                LocalDate fin = LocalDate.parse(fechaMax);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fechaCreacion"), fin));
            }

            if (estado == null || estado.trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("estado"), "Activo"));
            } else if (!estado.equalsIgnoreCase("Todos")) {
                predicates.add(criteriaBuilder.equal(root.get("estado"), estado));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<UsuarioEntity> entities = usuarioRepository.findAll(spec);

        return entities.stream()
                .map(e -> usuarioAdapter.toModel(e))
                .collect(Collectors.toList());
    }
}