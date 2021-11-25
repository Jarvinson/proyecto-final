package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ChatServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ChatServicioTest {

    @Autowired
    private ChatServicio chatServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ProductoServicio productoServicio;


    @Test
    public void registrarTest (){
        Usuario user1 = null;
        try {
            user1 = usuarioServicio.obtenerUsuario(1);
            Producto producto1 = productoServicio.obtenerProducto(1);
            Chat chat= new Chat(user1, producto1);
            Chat chatGuardada = chatServicio.registrarChat(chat);
            Assertions.assertNotNull(chatGuardada);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void actualizarTest(){
        Chat chatGuardada = null;
        try {
            chatGuardada = chatServicio.obtenerChat(1);
            chatGuardada.setCodigo(210);
            chatServicio.actualizarChat(chatGuardada);
            Chat chatBuscada = chatServicio.obtenerChat(1);
            Assertions.assertEquals(210, chatBuscada.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void listarTest(){
        List<Chat> chats =chatServicio.listarChats();
        chats.forEach( chat-> System.out.println(chat));
        Assertions.assertEquals(0, chats.size());
    }

    @Test
    public void eliminarTest(){

        try {
            chatServicio.eliminarChat(1);
            Chat chatBuscado = chatServicio.obtenerChat(1);
            Assertions.assertNull(chatBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void obtenerTest(){
        try {
            Chat chat = chatServicio.obtenerChat(1);
            Assertions.assertNotNull(chat);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
