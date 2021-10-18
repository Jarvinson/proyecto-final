
package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
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

import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de Chats
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){
        Usuario user1 = usuarioRepo.findById(1).orElse(null);
        Producto producto1 = productoRepo.findById(101).orElse(null);
        Chat chat= new Chat(200, user1, producto1);
        Chat chatGuardada = chatRepo.save(chat);

        Assertions.assertNotNull(chatGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de Chats
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        Chat chatGuardada = chatRepo.findById(201).orElse(null);
        Producto producto1 = productoRepo.findById(104).orElse(null);
        chatGuardada.setCodigo(210);

        chatRepo.save(chatGuardada);

        Chat chatBuscada = chatRepo.findById(201).orElse(null);
        Assertions.assertEquals(210, chatBuscada.getCodigo());
    }

    //Función que permite realizar las pruebas unitarias para el listado de chats
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Chat> ciudades =chatRepo.findAll();
        ciudades.forEach( chat-> System.out.println(chat));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de Chats
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        chatRepo.deleteById(201);
        Chat chatBuscado = chatRepo.findById(2).orElse(null);
        Assertions.assertNull(chatBuscado);
    }



    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Chat> lista = chatRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Chat> lista = chatRepo.findAll(Sort.by("codigo"));

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }
}