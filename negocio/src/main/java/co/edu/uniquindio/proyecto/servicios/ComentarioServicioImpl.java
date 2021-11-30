package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private ProductoRepo productoRepo;


    @Override
    public Comentario registrarComentario(Comentario c) throws Exception {
        return comentarioRepo.save(c);
    }

    @Override
    public Comentario actualizarComentario(Comentario c) throws Exception {
        Optional<Comentario> buscado = comentarioRepo.findById(c.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("El comentario no existe");
        }
        return comentarioRepo.save(c);
    }

    @Override
    public void eliminarComentario(Integer id) throws Exception {
        Optional<Comentario> buscado = comentarioRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("El comentario no existe");
        }
       comentarioRepo.deleteById(id);
    }

    @Override
    public Comentario obtenerComentario(Integer id) throws Exception {
        return comentarioRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ningún comentario"));
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }

    @Override
    public List<Comentario> listarComentariosRango(int calificacionMenor, int calificacionMayor) {
        return comentarioRepo.listarComentariosRango(calificacionMenor, calificacionMayor);
    }

    @Override
    public Integer calificacionPromedio(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);

        if(producto.isEmpty()){
            throw new Exception("El producto no existe");
        }
        return comentarioRepo.calidicacionPromedio(codigo);
    }
}
