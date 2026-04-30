package Project.ALMXN.models;

import java.time.LocalDate;

public class Usuario {

    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaCreacion;
    private String contraseña;
    private String rol;
    private String estado;

    public Usuario(int idUsuario, String nombres, String apellidos, String correo, LocalDate fechaCreacion, String contraseña, String rol, String estado){
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.contraseña = contraseña;
        this.rol = rol;
        this.estado = estado;
    }

    public Usuario(){
    }

    public int getIdUsuario() { return idUsuario; }
    public void seIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres;}

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos;}

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getContraseña() { return contraseña; }

    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

}
