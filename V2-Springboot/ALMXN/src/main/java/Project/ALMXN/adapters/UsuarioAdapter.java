package Project.ALMXN.adapters;


import Project.ALMXN.entitys.UsuarioEntity;
import Project.ALMXN.models.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UsuarioAdapter {

    public UsuarioEntity toEntity(Usuario usuario){
        if(usuario == null){
            return null;
        }
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setIdUsuario(usuario.getIdUsuario());
        usuarioEntity.setNombres(usuario.getNombres());
        usuarioEntity.setApellidos(usuario.getApellidos());
        usuarioEntity.setCorreo(usuario.getCorreo());
        usuarioEntity.setFechaCreacion(usuario.getFechaCreacion() != null ? usuario.getFechaCreacion() : LocalDate.now());
        usuarioEntity.setContrasena(usuario.getContrasena());
        usuarioEntity.setRol(usuario.getRol());
        usuarioEntity.setEstado(usuario.getEstadoUsuario());

        return usuarioEntity;
    }

    public Usuario toModel(UsuarioEntity usuarioEntity){
        if(usuarioEntity == null){
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(usuarioEntity.getIdUsuario());
        usuario.setNombres(usuarioEntity.getNombres());
        usuario.setApellidos(usuarioEntity.getApellidos());
        usuario.setCorreo(usuarioEntity.getCorreo());
        usuario.setFechaCreacion(usuarioEntity.getFechaCreacion());
        usuario.setContrasena(usuarioEntity.getContrasena());
        usuario.setRol(usuarioEntity.getRol());
        usuario.setEstadoUsuario(usuarioEntity.getEstado());

        return usuario;
    }
}
