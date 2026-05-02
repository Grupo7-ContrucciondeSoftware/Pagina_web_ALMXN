package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Categoria;

public interface CategoriaService {

    List<Categoria> obtenerTodasLasCategorias();

    void guardarCategoria(Categoria categoria);

    Categoria buscarCategoriaPorId (int idCategoria);

    void actualizarCategoria(Categoria categoria);

    List<Categoria> filtrarCategorias(String nombreFiltro);

}
