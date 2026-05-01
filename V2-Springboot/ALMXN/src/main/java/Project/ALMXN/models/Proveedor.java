package Project.ALMXN.models;

public class Proveedor {

    private int idProveedor;
    private String rucProveedor;
    private String razonSocialProveedor;
    private String telefonoProveedor;
    private String correoProveedor;

    public Proveedor() { }

    public Proveedor(int idProveedor, String rucProveedor, String razonSocialProveedor, String telefonoProveedor, String correoProveedor){
        this.idProveedor = idProveedor;
        this.rucProveedor = rucProveedor;
        this.razonSocialProveedor = razonSocialProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.correoProveedor = correoProveedor;
    }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getRucProveedor() { return rucProveedor; }
    public void setRucProveedor(String rucProveedor) { this.rucProveedor = rucProveedor; }

    public String getRazonSocialProveedor() { return razonSocialProveedor; }
    public void setRazonSocialProveedor(String razonSocialProveedor) { this.razonSocialProveedor = razonSocialProveedor; }

    public String getTelefonoProveedor() { return telefonoProveedor; }
    public void setTelefonoProveedor(String telefonoProveedor) { this.telefonoProveedor = telefonoProveedor; }

    public String getCorreoProveedor() { return correoProveedor; }
    public void setCorreoProveedor(String correoProveedor) { this.correoProveedor = correoProveedor; }

}


