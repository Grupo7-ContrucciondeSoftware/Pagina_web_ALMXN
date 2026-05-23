package Project.ALMXN.adapters;

import Project.ALMXN.entitys.CategoriaEntity;
import Project.ALMXN.models.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaAdapter {

    public CategoriaEntity toEntity(Categoria categoria){
        if(categoria == null){
            return null;
        }
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setIdCategoria(categoria.getIdCategoria());
        categoriaEntity.setNombreCategoria(categoria.getNombreCategoria());
        categoriaEntity.setDescripcionCategoria(categoria.getDescripcionCategoria());
        categoriaEntity.setEstadoCategoria(categoria.getEstadoCategoria());

        return categoriaEntity;
    }

    public Categoria toModel(CategoriaEntity categoriaEntity){
        if(categoriaEntity == null){
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setIdCategoria(categoriaEntity.getIdCategoria());
        categoria.setNombreCategoria(categoriaEntity.getNombreCategoria());
        categoria.setDescripcionCategoria(categoriaEntity.getDescripcionCategoria());
        categoria.setEstadoCategoria(categoriaEntity.getEstadoCategoria());

        return categoria;
    }

}
