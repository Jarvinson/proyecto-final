package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.servicios.ChatServicio;
import co.edu.uniquindio.proyecto.servicios.MensajeServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class MensajeServicioTest {

    @Autowired
    private MensajeServicio mensajeServicio;

    @Autowired
    private ChatServicio chatServicio;


    @Test
    public void registrarTest (){

        Chat chat = null;
        try {
            chat = chatServicio.obtenerChat(1);
            Mensaje mensaje = new Mensaje("Hola", "Juan", chat );
            Mensaje mensajeGuardada = mensajeServicio.registrarMensaje(mensaje);

            Assertions.assertNotNull(mensajeGuardada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void actualizarTest(){
        Mensaje mensajeGuardada = null;
        try {
            mensajeGuardada = mensajeServicio.obtenerMensaje(1);
            mensajeGuardada.setMensaje("Chao");
            mensajeServicio.actualizarMensaje(mensajeGuardada);
            Mensaje mensajeBuscada = mensajeServicio.obtenerMensaje(1);
            Assertions.assertEquals("Chao", mensajeBuscada.getMensaje());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listarTest(){
        List<Mensaje> mensajes =mensajeServicio.listarMensajes();
        mensajes.forEach( mensaje -> System.out.println(mensaje));

        Assertions.assertEquals(0, mensajes.size());
    }

    @Test
    public void eliminarTest(){

        try {
            mensajeServicio.eliminarMensaje(1);
            Mensaje mensajeBuscado = mensajeServicio.obtenerMensaje(1);
            Assertions.assertNull(mensajeBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void obtener(){
        try {
            Mensaje mensaje = mensajeServicio.obtenerMensaje(1);
            Assertions.assertNotNull(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
