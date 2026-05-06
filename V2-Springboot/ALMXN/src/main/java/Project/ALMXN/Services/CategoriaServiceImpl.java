package Project.ALMXN.Services;

import Project.ALMXN.models.Categoria;
import Project.ALMXN.Repository.CategoriaDAO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO categoriaDAO){
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias(){
        return categoriaDAO.listaCategorias();
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        categoria.setEstadoCategoria("Activo");
        categoriaDAO.guardarCategoria(categoria);
    }

    @Override
    public Categoria buscarCategoriaPorId(int idCategoria){
        return categoriaDAO.buscarCategoriaPorId(idCategoria);
    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
        categoriaDAO.actualizarCategoria(categoria);
    }

    @Override
    public void eliminarCategoria(int idCategoria){
        categoriaDAO.eliminarCategoria(idCategoria);
    }

    @Override
    public List<Categoria> filtrarCategorias(String nombreFiltro, String estadoFiltro){
        return categoriaDAO.filtrarCategorias(nombreFiltro, estadoFiltro);
    }

    @Override
    public void activarCategoria(int idCategoria){
        categoriaDAO.activarCategoria(idCategoria);
    }

}
