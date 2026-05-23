package Project.ALMXN.models;

import java.time.LocalDate;

public class Producto {
    private Long idProducto;
    private String codigoProducto;
    private LocalDate fechaCreacionProducto;
    private String nombreProducto;
    private Categoria categoria;
    private int stockActualProducto;
    private String unidadMedidaProducto;
    private double precioCostoProducto;
    private double precioVentaProducto;
    private String descripcionProducto;
    private String estadoProducto;

    public Producto() {}

    public Producto(Long idProducto, String codigoProducto, LocalDate fechaCreacionProducto ,String nombreProducto, Categoria categoria, int stockActualProducto, String unidadMedidaProducto, double precioCostoProducto, double precioVentaProducto, String descripcionProducto, String estadoProducto){

        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.fechaCreacionProducto = fechaCreacionProducto;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.stockActualProducto = stockActualProducto;
        this.unidadMedidaProducto = unidadMedidaProducto;
        this.precioCostoProducto = precioCostoProducto;
        this.precioVentaProducto = precioVentaProducto;
        this.descripcionProducto = descripcionProducto;
        this.estadoProducto = estadoProducto;

    }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }

    public LocalDate getFechaCreacionProducto() { return fechaCreacionProducto; }
    public void setFechaCreacionProducto(LocalDate fechaCreacionProducto) { this.fechaCreacionProducto = fechaCreacionProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public int getStockActualProducto() { return stockActualProducto; }
    public void setStockActualProducto(int stockActualProducto) { this.stockActualProducto = stockActualProducto; }

    public String getUnidadMedidaProducto() { return unidadMedidaProducto; }
    public void setUnidadMedidaProducto(String unidadMedidaProducto) { this.unidadMedidaProducto = unidadMedidaProducto; }

    public double getPrecioCostoProducto() { return precioCostoProducto; }
    public void setPrecioCostoProducto(double precioCostoProducto) { this.precioCostoProducto = precioCostoProducto; }

    public double getPrecioVentaProducto() { return precioVentaProducto; }
    public void setPrecioVentaProducto(double precioVentaProducto) { this.precioVentaProducto = precioVentaProducto; }

    public String getDescripcionProducto() { return descripcionProducto; }
    public void setDescripcionProducto(String descripcionProducto) { this.descripcionProducto = descripcionProducto; }

    public String getEstadoProducto() { return estadoProducto; }
    public void setEstadoProducto(String estadoProducto) { this.estadoProducto = estadoProducto; }
}