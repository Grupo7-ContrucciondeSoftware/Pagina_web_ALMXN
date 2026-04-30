package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Categoria;

public interface CategoriaService {

    public List<Categoria> obtenerTodasLasCategorias();

    public void guardarCategoria(Categoria categoria);

    public Categoria buscarCategoriaPorId (int idCategoria);

    public void actualizarCategoria(Categoria categoria);

}
