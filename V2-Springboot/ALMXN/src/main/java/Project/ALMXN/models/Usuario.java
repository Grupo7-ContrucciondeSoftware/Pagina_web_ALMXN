package Project.ALMXN.models;

import java.time.LocalDate;

public class Usuario {

    private Long idUsuario;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaCreacion;
    private String contrasena;
    private String rol;
    private String estadoUsuario;

    public Usuario() { }

    public Usuario(Long idUsuario, String nombres, String apellidos, String correo, LocalDate fechaCreacion, String contrasena, String rol, String estadoUsuario){
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estadoUsuario = estadoUsuario;
    }
    
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres;}

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos;}

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getEstadoUsuario() { return estadoUsuario; }
    public void setEstadoUsuario(String estadoUsuario) { this.estadoUsuario = estadoUsuario; }

}
