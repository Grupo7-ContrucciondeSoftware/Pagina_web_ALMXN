package Project.ALMXN.entitys;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity (name="producto")
public class ProductoEntity {

    @Id
    @Column(name="id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column (name = "codigo")
    private String codigoProducto;

    @Column (name = "fecha_creacion")
    private LocalDate fechaCreacionProducto;

    @Column (name = "nombre")
    private String nombreProducto;

    @Column (name = "stock_actual")
    private int stockActualProducto;

    @Column (name = "unidad_medida")
    private String unidadMedidaProducto;

    @Column (name = "precio_costo")
    private double precioCostoProducto;

    @Column (name = "precio_venta")
    private double precioVentaProducto;

    @Column (name = "descripcion")
    private String descripcionProducto;

    @Column (name = "estado")
    private String estadoProducto;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private CategoriaEntity categoria;

    public ProductoEntity(Long idProducto, String codigoProducto, LocalDate fechaCreacionProducto, String nombreProducto, int stockActualProducto, String unidadMedidaProducto, double precioCostoProducto, double precioVentaProducto, String descripcionProducto, String estadoProducto) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.fechaCreacionProducto = fechaCreacionProducto;
        this.nombreProducto = nombreProducto;
        this.stockActualProducto = stockActualProducto;
        this.unidadMedidaProducto = unidadMedidaProducto;
        this.precioCostoProducto = precioCostoProducto;
        this.precioVentaProducto = precioVentaProducto;
        this.descripcionProducto = descripcionProducto;
        this.estadoProducto = estadoProducto;
    }

    public ProductoEntity(){}

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public LocalDate getFechaCreacionProducto() {
        return fechaCreacionProducto;
    }

    public void setFechaCreacionProducto(LocalDate fechaCreacionProducto) {
        this.fechaCreacionProducto = fechaCreacionProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStockActualProducto() {
        return stockActualProducto;
    }

    public void setStockActualProducto(int stockActualProducto) {
        this.stockActualProducto = stockActualProducto;
    }

    public String getUnidadMedidaProducto() {
        return unidadMedidaProducto;
    }

    public void setUnidadMedidaProducto(String unidadMedidaProducto) {
        this.unidadMedidaProducto = unidadMedidaProducto;
    }

    public double getPrecioCostoProducto() {
        return precioCostoProducto;
    }

    public void setPrecioCostoProducto(double precioCostoProducto) {
        this.precioCostoProducto = precioCostoProducto;
    }

    public double getPrecioVentaProducto() {
        return precioVentaProducto;
    }

    public void setPrecioVentaProducto(double precioVentaProducto) {
        this.precioVentaProducto = precioVentaProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
}
