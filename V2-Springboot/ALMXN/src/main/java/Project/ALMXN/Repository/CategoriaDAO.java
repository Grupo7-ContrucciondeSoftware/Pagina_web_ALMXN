package Project.ALMXN.Repository;

import java.util.List;
import Project.ALMXN.models.Categoria;

public interface CategoriaDAO {

    List<Categoria> listaCategorias();

    void guardarCategoria(Categoria categoria);

    Categoria buscarCategoriaPorId(int idCategoria);

    void actualizarCategoria(Categoria categoria);
}
