package Project.ALMXN.models;

public class Categoria {

    private Integer idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private String estadoCategoria;

    public Categoria() {}

    public Categoria(Integer idCategoria, String nombreCategoria, String descripcionCategoria, String estadoCategoria){
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.estadoCategoria = estadoCategoria;
    }

    public Integer getIdCategoria() { return idCategoria;}
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria;}

    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }

    public String getDescripcionCategoria() { return descripcionCategoria; }
    public void setDescripcionCategoria(String descripcionCategoria) { this.descripcionCategoria = descripcionCategoria; }

    public String getEstadoCategoria() { return estadoCategoria; }
    public void setEstadoCategoria(String estadoCategoria) { this.estadoCategoria = estadoCategoria; }

}
