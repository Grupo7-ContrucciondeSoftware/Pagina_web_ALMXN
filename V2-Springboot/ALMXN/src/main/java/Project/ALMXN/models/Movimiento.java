package Project.ALMXN.models;

import java.time.LocalDateTime;

public class Movimiento {
    private Long idMovimiento;
    private String tipoMovimiento;
    private LocalDateTime fechaMovimiento;
    private String motivoMovimiento;
    private String observacionesMovimiento;
    private Usuario usuario;
    private Proveedor proveedor;
    private Double totalMovimiento;

    public Movimiento() {
    }

    public Movimiento(Long idMovimiento, String tipoMovimiento, LocalDateTime fechaMovimiento, String motivoMovimiento,
            String observacionesMovimiento, Usuario usuario, Proveedor proveedor,
            double totalMovimiento) {
        this.idMovimiento = idMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.motivoMovimiento = motivoMovimiento;
        this.observacionesMovimiento = observacionesMovimiento;
        this.usuario = usuario;
        this.proveedor = proveedor;
        this.totalMovimiento = totalMovimiento;

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

    public void setObservacionesMovimiento(String observacionesMovimiento) {
        this.observacionesMovimiento = observacionesMovimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Double getTotalMovimiento() {
        return totalMovimiento;
    }

    public void setTotalMovimiento(Double totalMovimiento) {
        this.totalMovimiento = totalMovimiento;
    }
}
