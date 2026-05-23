package Project.ALMXN.models;

public class Proveedor {

    private Long idProveedor;
    private String rucProveedor;
    private String razonSocialProveedor;
    private String telefonoProveedor;
    private String correoProveedor;
    private String estadoProveedor;

    public Proveedor() { }

    public Proveedor(Long idProveedor, String rucProveedor, String razonSocialProveedor, String telefonoProveedor, String correoProveedor, String estadoProveedor){
        this.idProveedor = idProveedor;
        this.rucProveedor = rucProveedor;
        this.razonSocialProveedor = razonSocialProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.correoProveedor = correoProveedor;
        this.estadoProveedor = estadoProveedor;
    }

    public Long getIdProveedor() { return idProveedor; }
    public void setIdProveedor(Long idProveedor) { this.idProveedor = idProveedor; }

    public String getRucProveedor() { return rucProveedor; }
    public void setRucProveedor(String rucProveedor) { this.rucProveedor = rucProveedor; }

    public String getRazonSocialProveedor() { return razonSocialProveedor; }
    public void setRazonSocialProveedor(String razonSocialProveedor) { this.razonSocialProveedor = razonSocialProveedor; }

    public String getTelefonoProveedor() { return telefonoProveedor; }
    public void setTelefonoProveedor(String telefonoProveedor) { this.telefonoProveedor = telefonoProveedor; }

    public String getCorreoProveedor() { return correoProveedor; }
    public void setCorreoProveedor(String correoProveedor) { this.correoProveedor = correoProveedor; }

    public String getEstadoProveedor() { return estadoProveedor; }
    public void setEstadoProveedor(String estadoProveedor) { this.estadoProveedor = estadoProveedor; }
}


