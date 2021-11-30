package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{

    @Autowired
    private CategoriaRepo categoriaRepo;


    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Integer id) throws Exception {
        return categoriaRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ninguna categoría"));
    }


    @Override
    public Categoria registrarCategoria (Categoria c) throws Exception{
        Optional<Categoria> buscado = categoriaRepo.findById(c.getCodigo());

        if ( buscado.isPresent()){
            throw new Exception("El código de la categoría ya existe");
        }
        return categoriaRepo.save(c);
    }

    @Override
    public Categoria actualizarCategoria(Categoria c) throws Exception{
        Optional<Categoria> buscado = categoriaRepo.findById(c.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("La categoría no existe");
        }
        return categoriaRepo.save(c);

    }

    @Override
    public void eliminarCategoria(Integer id) throws Exception{

        Optional<Categoria> buscado = categoriaRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("La categoría no existe");
        }
        categoriaRepo.deleteById(id);

    }

   @Override
    public List<Categoria> filtrarNombreCategoria(String categoria){

        List<Categoria> lista = categoriaRepo.findAllByNombreContains(categoria);
        return lista;
    }

    @Override
    public List<Object[]> obtenerCategoriaMasUsada() {
        return categoriaRepo.obtenerCategoriaMasUsada();
    }

}
