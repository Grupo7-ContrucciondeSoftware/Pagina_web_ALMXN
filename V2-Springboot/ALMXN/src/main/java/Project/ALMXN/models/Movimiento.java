package Project.ALMXN.models;

import java.time.LocalDate;

public class Movimiento {
    private int idMovimiento;
    private String tipoMovimiento;
    private LocalDate fechaMovimiento;
    private String motivoMovimiento;
    private String destinoMovimiento;
    private String observacionesMovimiento;
    private Usuario usuario;
    private Proveedor proveedor;
    private Double totalMovimiento;

    public Movimiento() {
    }

    public Movimiento(int idMovimiento, String tipoMovimiento, LocalDate fechaMovimiento, String motivoMovimiento,
            String destinoMovimiento, String observacionesMovimiento, Usuario usuario, Proveedor proveedor,
            double totalMovimiento) {
        this.idMovimiento = idMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.motivoMovimiento = motivoMovimiento;
        this.destinoMovimiento = destinoMovimiento;
        this.observacionesMovimiento = observacionesMovimiento;
        this.usuario = usuario;
        this.proveedor = proveedor;
        this.totalMovimiento = totalMovimiento;

    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getMotivoMovimiento() {
        return motivoMovimiento;
    }

    public void setMotivoMovimiento(String motivoMovimiento) {
        this.motivoMovimiento = motivoMovimiento;
    }

    public String getDestinoMovimiento() {
        return destinoMovimiento;
    }

    public void setDestinoMovimiento(String destinoMovimiento) {
        this.destinoMovimiento = destinoMovimiento;
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
