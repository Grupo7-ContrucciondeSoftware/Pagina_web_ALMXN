package Project.ALMXN.Services;

import java.util.List;
import Project.ALMXN.models.Categoria;

public interface CategoriaService {

    List<Categoria> obtenerTodasLasCategorias();

    Categoria guardarCategoria(Categoria categoria);

    Categoria buscarCategoriaPorId (int idCategoria);

    Categoria actualizarCategoria(Categoria categoria);

    void eliminarCategoria(int idCategoria);

    List<Categoria> filtrarCategorias(String nombreFiltro, String estadoFiltro);

    void activarCategoria(int idCategoria);

}
