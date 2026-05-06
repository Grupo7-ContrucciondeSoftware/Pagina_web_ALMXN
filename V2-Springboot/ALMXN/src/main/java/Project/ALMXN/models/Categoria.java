package Project.ALMXN.models;

public class Categoria {

    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private String estadoCategoria;

    public Categoria() {}

    public Categoria(int idCategoria, String nombreCategoria, String descripcionCategoria, String estadoCategoria){
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.estadoCategoria = estadoCategoria;
    }

    public int getIdCategoria() { return idCategoria;}
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria;}

    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }

    public String getDescripcionCategoria() { return descripcionCategoria; }
    public void setDescripcionCategoria(String descripcionCategoria) { this.descripcionCategoria = descripcionCategoria; }

    public String getEstadoCategoria() { return estadoCategoria; }
    public void setEstadoCategoria(String estadoCategoria) { this.estadoCategoria = estadoCategoria; }

}
