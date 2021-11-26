package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ComentarioServicioTest {


    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @Test
    public void registrarTest (){
        Usuario usuario = null;
        try {
            usuario = usuarioServicio.obtenerUsuario(1);
            Producto producto = productoServicio.obtenerProducto(1);
            Comentario comentario = new Comentario("Buen producto",  null, 4, producto , usuario );
            Comentario comentarioGuardada = comentarioServicio.registrarComentario(comentario);

            Assertions.assertNotNull(comentarioGuardada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void actualizarTest(){
        Comentario comentarioGuardado = null;
        try {
            comentarioGuardado = comentarioServicio.obtenerComentario(1);
            comentarioGuardado.setMensaje("Regular estado");
            comentarioServicio.actualizarComentario(comentarioGuardado);
            Comentario comentarioBuscada =comentarioServicio.obtenerComentario(1);

            Assertions.assertEquals("Regular estado", comentarioBuscada.getMensaje());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listarTest(){
        List<Comentario> comentarios =comentarioServicio.listarComentarios();
        comentarios.forEach( comentario -> System.out.println(comentario));

        Assertions.assertEquals(4, comentarios.size());
    }

    @Test
    public void eliminarTest(){

        try {
            comentarioServicio.eliminarComentario(1);
            Comentario comentarioBuscado = comentarioServicio.obtenerComentario(1);
            Assertions.assertNull(comentarioBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void comentariosPorCalificacion(){
        List<Comentario> lista = comentarioServicio.listarComentariosRango(3, 4);
        lista.forEach(u-> System.out.println(u));

        Assertions.assertEquals(0, lista.size());
    }
}
