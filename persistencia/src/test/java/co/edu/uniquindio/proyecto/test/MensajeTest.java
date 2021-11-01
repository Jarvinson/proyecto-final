package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


//Esta clase contiene los métodos para realizar las pruebas unitarias al objeto Mensaje
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    @Autowired
    private ChatRepo chatRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de mensaje
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){

        Chat chat = chatRepo.findById(200).orElse(null);
        Mensaje mensaje = new Mensaje("Hola", "Juan", chat );
        Mensaje mensajeGuardada = mensajeRepo.save(mensaje);

        Assertions.assertNotNull(mensajeGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de mensaje
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        Mensaje mensajeGuardada = mensajeRepo.findById(2).orElse(null);
        mensajeGuardada.setMensaje("Chao");

        mensajeRepo.save(mensajeGuardada);

        Mensaje mensajeBuscada = mensajeRepo.findById(2).orElse(null);
        Assertions.assertEquals("Chao", mensajeBuscada.getMensaje());
    }

    //Función que permite realizar las pruebas unitarias para el listado de mensaje
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Mensaje> mensajes =mensajeRepo.findAll();
        mensajes.forEach( mensaje -> System.out.println(mensaje));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de mensaje
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        mensajeRepo.deleteById(3);
        Mensaje mensajeBuscado = mensajeRepo.findById(3).orElse(null);
        Assertions.assertNull(mensajeBuscado);
    }

    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Mensaje> lista = mensajeRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Mensaje> lista = mensajeRepo.findAll(Sort.by("codigo"));

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }
}
