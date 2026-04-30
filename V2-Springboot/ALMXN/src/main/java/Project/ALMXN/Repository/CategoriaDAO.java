package Project.ALMXN.Repository;

import java.util.List;
import Project.ALMXN.models.Categoria;

public interface CategoriaDAO {

    List<Categoria> listaCategorias();

    public void guardarCategoria(Categoria categoria);

    public Categoria buscarCategoriaPorId(int idCategoria);

    public void actualizarCategoria(Categoria categoria);
}
