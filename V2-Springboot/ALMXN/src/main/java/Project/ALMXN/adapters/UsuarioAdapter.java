package Project.ALMXN.adapters;


import Project.ALMXN.entitys.UsuarioEntity;
import Project.ALMXN.models.Usuario;
import org.springframework.stereotype.Component;

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
        usuarioEntity.setFechaCreacion(usuario.getFechaCreacion());
        usuarioEntity.setContraseña(usuario.getContraseña());
        usuarioEntity.setRol(usuario.getRol());
        usuarioEntity.setEstadoUsuario(usuario.getEstadoUsuario());

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
        usuario.setContraseña(usuarioEntity.getContraseña());
        usuario.setRol(usuarioEntity.getRol());
        usuario.setEstadoUsuario(usuarioEntity.getEstadoUsuario());

        return usuario;
    }
}
