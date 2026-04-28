package Project.ALMXN.models;

import java.time.LocalDate;

public class Producto {
    private int idProducto;
    private String codigoProducto;
    private LocalDate fechaCreacionProducto;
    private String nombreProducto;
    private Categoria categoria;
    private String unidadMedidaProducto;
    private int stockActualProducto;
    private int stockMinimoProducto;
    private double precioCostoProducto;
    private double precioVentaProducto;
    private String descripcionProducto;

    public Producto() {}

    public Producto(int idProducto, String codigoProducto, LocalDate fechaCreacionProducto ,String nombreProducto, Categoria categoria, String unidadMedidaProducto, int stockActualProducto, int stockMinimoProducto, double precioCostoProducto, double precioVentaProducto, String descripcionProducto){

        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.fechaCreacionProducto = fechaCreacionProducto;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.unidadMedidaProducto = unidadMedidaProducto;
        this.stockActualProducto = stockActualProducto;
        this.stockMinimoProducto = stockMinimoProducto;
        this.precioCostoProducto = precioCostoProducto;
        this.precioVentaProducto = precioVentaProducto;
        this.descripcionProducto = descripcionProducto;

    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }

    public LocalDate getFechaCreacionProducto() { return fechaCreacionProducto; }
    public void setFechaCreacionProducto(LocalDate fechaCreacionProducto) { this.fechaCreacionProducto = fechaCreacionProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getUnidadMedidaProducto() { return unidadMedidaProducto; }
    public void setUnidadMedidaProducto(String unidadMedidaProducto) { this.unidadMedidaProducto = unidadMedidaProducto; }

    public int getStockActualProducto() { return stockActualProducto; }
    public void setStockActualProducto(int stockActualProducto) { this.stockActualProducto = stockActualProducto; }

    public int getStockMinimoProducto() { return stockMinimoProducto; }
    public void setStockMinimoProducto(int stockMinimoProducto) { this.stockMinimoProducto = stockMinimoProducto; }

    public double getPrecioCostoProducto() { return precioCostoProducto; }
    public void setPrecioCostoProducto(double precioCostoProducto) { this.precioCostoProducto = precioCostoProducto; }

    public double getPrecioVentaProducto() { return precioVentaProducto; }
    public void setPrecioVentaProducto(double precioVentaProducto) { this.precioVentaProducto = precioVentaProducto; }

    public String getDescripcionProducto() { return descripcionProducto; }
    public void setDescripcionProducto(String descripcionProducto) { this.descripcionProducto = descripcionProducto; }
}