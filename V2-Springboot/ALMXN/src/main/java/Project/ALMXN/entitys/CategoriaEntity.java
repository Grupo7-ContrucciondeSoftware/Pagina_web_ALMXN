package Project.ALMXN.entitys;

import jakarta.persistence.*;

import java.util.List;

@Entity (name = "categoria")
public class CategoriaEntity {

    @Id
    @Column (name = "id_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column (name = "nombre")
    private String nombreCategoria;

    @Column (name = "descripcion")
    private String descripcionCategoria;

    @Column (name = "estado")
    private String estadoCategoria;

    @OneToMany(mappedBy = "categoria")
    private List<ProductoEntity> productos;


    public CategoriaEntity(Long idCategoria, String nombreCategoria, String descripcionCategoria, String estadoCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.estadoCategoria = estadoCategoria;
    }

    public CategoriaEntity(){
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public String getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(String estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }
}
