package Project.ALMXN.entitys;

import jakarta.persistence.*;

@Entity(name="detalle_movimiento")
public class DetalleMovimientoEntity {

    @Id
    @Column(name="id_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleMovimiento;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidadDetalleMovimiento;

    @Column(name = "precio_unitario")
    private double precioUnitarioDetalleMovimiento;

    @Column(name = "subtotal")
    private double subtotalDetalleMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movimiento")
    private MovimientoEntity movimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    public DetalleMovimientoEntity(Long idDetalleMovimiento, Integer cantidadDetalleMovimiento, double precioUnitarioDetalleMovimiento, double subtotalDetalleMovimiento) {
        this.idDetalleMovimiento = idDetalleMovimiento;
        this.cantidadDetalleMovimiento = cantidadDetalleMovimiento;
        this.precioUnitarioDetalleMovimiento = precioUnitarioDetalleMovimiento;
        this.subtotalDetalleMovimiento = subtotalDetalleMovimiento;
    }

    public DetalleMovimientoEntity(){}

    public Long getIdDetalleMovimiento() {
        return idDetalleMovimiento;
    }

    public void setIdDetalleMovimiento(Long idDetalle) {
        this.idDetalleMovimiento = idDetalle;
    }

    public Integer getCantidadDetalleMovimiento() {
        return cantidadDetalleMovimiento;
    }

    public void setCantidadDetalleMovimiento(Integer cantidadDetalleMovimiento) {
        this.cantidadDetalleMovimiento = cantidadDetalleMovimiento;
    }

    public double getPrecioUnitarioDetalleMovimiento() {
        return precioUnitarioDetalleMovimiento;
    }

    public void setPrecioUnitarioDetalleMovimiento(double precioUnitarioDetalleMovimiento) {
        this.precioUnitarioDetalleMovimiento = precioUnitarioDetalleMovimiento;
    }

    public double getSubtotalDetalleMovimiento() {
        return subtotalDetalleMovimiento;
    }

    public void setSubtotalDetalleMovimiento(double subtotalDetalleMovimiento) {
        this.subtotalDetalleMovimiento = subtotalDetalleMovimiento;
    }

    public MovimientoEntity getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoEntity movimiento) {
        this.movimiento = movimiento;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
}
