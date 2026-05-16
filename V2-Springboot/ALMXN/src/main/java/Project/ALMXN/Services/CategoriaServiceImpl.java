package Project.ALMXN.Services;

import Project.ALMXN.models.Categoria;
import Project.ALMXN.Repository.CategoriaDAO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO categoriaDAO){
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias(){
        // return categoriaDAO.listaCategorias(); //F3

        // List<Categoria> lista = new ArrayList<>(); //F2
        // lista.addAll(categoriaDAO.listaCategorias()); //F2
        // return lista; //F2

        return new ArrayList<>();
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        // if (categoria.getIdCategoria() == null){ //F3
        //     categoria.setEstadoCategoria("Activo"); //F3
        //     categoriaDAO.guardarCategoria(categoria); //F3
        // } else{ //F3
        //     categoriaDAO.actualizarCategoria(categoria); //F3
        // } //F3
        // return categoria; //F3
        
        Categoria categoria_resp = new Categoria();
        // categoria_resp.setNombreCategoria(categoria.getNombreCategoria()); //F2
        // categoria_resp.setDescripcionCategoria(categoria.getDescripcionCategoria()); //F2
        // categoria_resp.setEstadoCategoria("Activo"); //F2
        return categoria_resp;
    }

    @Override
    public Categoria buscarCategoriaPorId(int idCategoria){
        return categoriaDAO.buscarCategoriaPorId(idCategoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        // categoriaDAO.actualizarCategoria(categoria); //F3
        // return categoria; //F3

        Categoria categoria_resp = new Categoria();
        // categoria_resp.setIdCategoria(categoria.getIdCategoria()); //F2
        // categoria_resp.setNombreCategoria(categoria.getNombreCategoria()); //F2
        // categoria_resp.setDescripcionCategoria(categoria.getDescripcionCategoria()); //F2
        return categoria_resp;
    }

    @Override
    public void eliminarCategoria(int idCategoria){
        // categoriaDAO.eliminarCategoria(idCategoria); //F3

        // System.out.println("Categoría " + idCategoria + " desactivada"); //F2
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
