package Project.ALMXN.entitys;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name="movimiento")
public class MovimientoEntity {

    @Id
    @Column(name="id_movimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimiento;

    @Column (name = "tipo_movimiento")
    private String tipoMovimiento;

    @Column (name = "fecha_movimiento")
    private LocalDateTime fechaMovimiento;

    @Column (name = "motivo")
    private String motivoMovimiento;

    @Column (name = "observaciones")
    private String observacionesMovimiento;

    @Column (name = "total_movimiento")
    private double totalMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_proveedor")
    private ProveedorEntity proveedor;

    @OneToMany(mappedBy = "movimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleMovimientoEntity> detalles = new ArrayList<>();

    public MovimientoEntity(Long idMovimiento, String tipoMovimiento, LocalDateTime fechaMovimiento, String motivoMovimiento, String observacionesMovimiento, double totalMovimiento) {
        this.idMovimiento = idMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.motivoMovimiento = motivoMovimiento;
        this.observacionesMovimiento = observacionesMovimiento;
        this.totalMovimiento = totalMovimiento;
    }

    public MovimientoEntity(){

    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getMotivoMovimiento() {
        return motivoMovimiento;
    }

    public void setMotivoMovimiento(String motivoMovimiento) {
        this.motivoMovimiento = motivoMovimiento;
    }

    public String getObservacionesMovimiento() {
        return observacionesMovimiento;
    }

    public void setObservacionesMovimiento(String observacionesMovimiento) { this.observacionesMovimiento = observacionesMovimiento; }

    public double getTotalMovimiento() {
        return totalMovimiento;
    }

    public void setTotalMovimiento(double totalMovimiento) {
        this.totalMovimiento = totalMovimiento;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetalleMovimientoEntity> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleMovimientoEntity> detalles) {
        this.detalles = detalles;
    }
}
