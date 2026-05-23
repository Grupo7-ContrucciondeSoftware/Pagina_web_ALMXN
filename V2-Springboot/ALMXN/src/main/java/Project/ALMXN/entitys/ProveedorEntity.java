package Project.ALMXN.entitys;

import jakarta.persistence.*;

@Entity (name = "proveedor")
public class ProveedorEntity {

    @Id
    @Column (name = "id_proveedor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column (name = "ruc")
    private String rucProveedor;

    @Column (name = "razon_social")
    private String razonSocialProveedor;

    @Column (name = "telefono")
    private String telefonoProveedor;

    @Column (name = "correo")
    private String correoProveedor;

    @Column (name = "estado")
    private String estadoProveedor;

    public ProveedorEntity(Long idProveedor, String rucProveedor, String razonSocialProveedor, String telefonoProveedor, String correoProveedor, String estadoProveedor) {
        this.idProveedor = idProveedor;
        this.rucProveedor = rucProveedor;
        this.razonSocialProveedor = razonSocialProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.correoProveedor = correoProveedor;
        this.estadoProveedor = estadoProveedor;
    }

    public ProveedorEntity(){}

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getCorreoProveedor() {
        return correoProveedor;
    }

    public void setCorreoProveedor(String correoProveedor) {
        this.correoProveedor = correoProveedor;
    }

    public String getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(String estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }
}
