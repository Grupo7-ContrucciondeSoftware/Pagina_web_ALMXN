package Project.ALMXN.Services;

import Project.ALMXN.Repository.UsuarioRepository;
import Project.ALMXN.adapters.UsuarioAdapter;
import Project.ALMXN.entitys.UsuarioEntity;
import Project.ALMXN.models.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioAdapter usuarioAdapter;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioAdapter usuarioAdapter) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioAdapter = usuarioAdapter;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return filtrarUsuario(null, null, "Activo", null);
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioEntity entity;
        boolean esNuevo = (usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0);

        if (esNuevo) {
            if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
                throw new RuntimeException("El correo electrónico ya se encuentra registrado en el sistema.");
            }
            usuario.setEstadoUsuario("Activo");
            entity = usuarioAdapter.toEntity(usuario);
        } else {
            entity = usuarioRepository.findById(usuario.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuario.getIdUsuario()));

            if (!entity.getCorreo().equalsIgnoreCase(usuario.getCorreo()) &&
                    usuarioRepository.existsByCorreo(usuario.getCorreo())) {
                throw new RuntimeException("El correo electrónico ya se encuentra registrado en el sistema.");
            }

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
    public void eliminarUsuario(Long idUsuario) {
        UsuarioEntity entity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + idUsuario));
        entity.setEstado("Inactivo");
        usuarioRepository.save(entity);
    }

    @Transactional
    public void activarUsuario(Long idUsuario) {
        UsuarioEntity entity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + idUsuario));
        entity.setEstado("Activo");
        usuarioRepository.save(entity);
    }

    public Usuario validarLogin(String correo, String contrasena) {
        Optional<UsuarioEntity> entityOptional = usuarioRepository
                .findByCorreoAndContrasenaAndEstado(correo, contrasena, "Activo");

        if (entityOptional.isPresent()) {
            return usuarioAdapter.toModel(entityOptional.get());
        }
        return null;
    }

    public List<Usuario> filtrarUsuario(String nombres, String rol, String estado, String fecha) {

        // 1. Traemos todos los usuarios de la base de datos
        List<UsuarioEntity> todos = usuarioRepository.findAll();

        // 2. Lista donde guardaremos los que pasen los filtros
        List<UsuarioEntity> resultado = new ArrayList<>();

        // 3. Recorremos uno por uno y aplicamos los filtros
        for (UsuarioEntity usuario : todos) {

            // Filtro por nombres: verifica que contenga el texto
            if (nombres != null && !nombres.trim().isEmpty()) {
                boolean contieneNombre = usuario.getNombres()
                        .toLowerCase()
                        .contains(nombres.trim().toLowerCase());
                if (!contieneNombre) {
                    continue;
                }
            }

            // Filtro por rol: verifica que contenga el texto
            if (rol != null && !rol.trim().isEmpty()) {
                boolean contieneRol = usuario.getRol()
                        .toLowerCase()
                        .contains(rol.trim().toLowerCase());
                if (!contieneRol) {
                    continue;
                }
            }

            // Filtro por fecha: verifica que coincida exacto
            if (fecha != null && !fecha.isEmpty()) {
                LocalDate fechaLocal = LocalDate.parse(fecha);
                if (!usuario.getFechaCreacion().equals(fechaLocal)) {
                    continue;
                }
            }

            // Filtro por estado
            if (estado == null || estado.trim().isEmpty()) {
                // Sin estado → solo "Activo"
                if (!usuario.getEstado().equals("Activo")) {
                    continue;
                }
            } else if (!estado.equalsIgnoreCase("Todos")) {
                // Estado específico → filtramos exacto
                if (!usuario.getEstado().equalsIgnoreCase(estado)) {
                    continue;
                }
            }

            resultado.add(usuario);
        }

        // 4. Convertimos las entidades a modelos y retornamos
        List<Usuario> usuarios = new ArrayList<>();
        for (UsuarioEntity entity : resultado) {
            usuarios.add(usuarioAdapter.toModel(entity));
        }
        return usuarios;
    }
}