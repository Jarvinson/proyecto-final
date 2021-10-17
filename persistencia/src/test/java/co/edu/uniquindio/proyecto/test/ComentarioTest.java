package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de comentarios
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){
        Usuario usuario = usuarioRepo.findById(4).orElse(null);
        Producto producto = productoRepo.findById(100).orElse(null);

        Comentario comentario = new Comentario(1, "Buen producto",  "Que bien", LocalDateTime.now(), 4, producto , usuario );
        Comentario comentarioGuardada = comentarioRepo.save(comentario);

        Assertions.assertNotNull(comentarioGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de comentarios
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        Comentario comentarioGuardado = comentarioRepo.findById(2).orElse(null);
        comentarioGuardado.setMensaje("Regular estado");

        comentarioRepo.save(comentarioGuardado);

        Comentario comentarioBuscada = comentarioRepo.findById(2).orElse(null);
        Assertions.assertEquals("Regular estado", comentarioBuscada.getMensaje());
    }

    //Función que permite realizar las pruebas unitarias para el listado de comentarios
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Comentario> comentarios =comentarioRepo.findAll();
        comentarios.forEach( comentario -> System.out.println(comentario));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de comentarios
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        comentarioRepo.deleteById(2);
        Comentario comentarioBuscado = comentarioRepo.findById(2).orElse(null);
        Assertions.assertNull(comentarioBuscado);
    }

    //Función que permite realizar las pruebas unitarias para filtrar el nombre de las comentarios
    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){

        List<Comentario> lista = comentarioRepo.findAllByMensajeContains("Buen");
        lista.forEach(u-> System.out.println(u));
    }

    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Comentario> lista = comentarioRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Comentario> lista = comentarioRepo.findAll(Sort.by("mensaje"));

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que lista los comentarios por rango de calificación
    @Test
    @Sql("classpath:data.sql")
    public void comentariosPorCalificacion(){
        List<Comentario> lista = comentarioRepo.listarComentariosRango(3, 4);
        lista.forEach(u-> System.out.println(u));
    }
}
