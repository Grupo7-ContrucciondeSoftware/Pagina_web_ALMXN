package Project.ALMXN.Services;

import Project.ALMXN.models.Categoria;
import Project.ALMXN.Repository.CategoriaDAO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaDAO CategoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO CategoriaDAO){

        this.CategoriaDAO = CategoriaDAO;
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias(){
        return CategoriaDAO.listaCategorias();
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        CategoriaDAO.guardar(categoria);
    }

}
