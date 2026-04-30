package Project.ALMXN.models;

public class DetalleMovimiento {
    private int idDetalleMovimiento;
    private Movimiento movimiento;
    private Producto producto;
    private int cantidadDetalleMovimiento;
    private double precioUnitarioDetalleMovimiento;
    private double subtotalDetalleMovimiento;

    public DetalleMovimiento() {}

    public int getIdDetalleMovimiento() { return idDetalleMovimiento; }
    public void setIdDetalleMovimiento(int idDetalleMovimiento) { this.idDetalleMovimiento = idDetalleMovimiento; }

    public Movimiento getMovimiento() { return movimiento; }
    public void setMovimiento(Movimiento movimiento) { this.movimiento = movimiento; }

    public Producto getProducto() { return producto;}
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidadDetalleMovimiento() { return cantidadDetalleMovimiento; }
    public void setCantidadDetalleMovimiento(int cantidadDetalleMovimiento) { this.cantidadDetalleMovimiento = cantidadDetalleMovimiento; }

    public double getPrecioUnitarioDetalleMovimiento() { return precioUnitarioDetalleMovimiento; }
    public void setPrecioUnitarioDetalleMovimiento(double precioUnitarioDetalleMovimiento) { this.precioUnitarioDetalleMovimiento = precioUnitarioDetalleMovimiento; }

    public double getSubtotalDetalleMovimiento() { return subtotalDetalleMovimiento; }
    public void setSubtotalDetalleMovimiento(double subtotalDetalleMovimiento) { this.subtotalDetalleMovimiento = subtotalDetalleMovimiento; }
}
