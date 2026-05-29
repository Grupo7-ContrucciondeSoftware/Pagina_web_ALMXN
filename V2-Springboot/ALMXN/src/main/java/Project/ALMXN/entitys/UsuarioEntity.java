package Project.ALMXN.entitys;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity (name = "usuario")
public class UsuarioEntity {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "correo")
    private String correo;

    @Column(name = "fechacreacion")
    private LocalDate fechaCreacion;

    @Column(name = "contraseña")
    private String contrasena;

    @Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "usuario")
    private List<MovimientoEntity> movimientos;

    public UsuarioEntity(Long idUsuario, String nombres, String apellidos, String correo, LocalDate fechaCreacion, String contrasena, String rol, String estado) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = estado;
    }

    public UsuarioEntity(){}

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
