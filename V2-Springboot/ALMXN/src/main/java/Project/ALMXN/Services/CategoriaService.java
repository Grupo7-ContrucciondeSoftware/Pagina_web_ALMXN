package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Categoria;

public interface CategoriaService {

    List<Categoria> obtenerTodasLasCategorias();

    void guardarCategoria(Categoria categoria);

    Categoria buscarCategoriaPorId (int idCategoria);

    void actualizarCategoria(Categoria categoria);

    void eliminarCategoria(int idCategoria);

    List<Categoria> filtrarCategorias(String nombreFiltro, String estadoFiltro);

    void activarCategoria(int idCategoria);

}
