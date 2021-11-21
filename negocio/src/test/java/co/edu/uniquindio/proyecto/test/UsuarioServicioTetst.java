package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTetst {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTestTest(){
        Usuario u = new Usuario(123, "pepito", "pepito@email.com", "12345", "pepito12", null);
        try {
            Usuario respuesta = usuarioServicio.registrarUsuario(u);
            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarTest(){
        try {
            usuarioServicio.eliminarUsuario(123);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listarTest(){
        List<Usuario> lista = usuarioServicio.listarUsuarios();
        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarTest(){
        try {
            Usuario u = usuarioServicio.obtenerUsuario(123);
            u.setPassword("asdfr456");
            Usuario respuesta = usuarioServicio.actualizarUsuario(u);
            Assertions.assertEquals("asdfr456", respuesta.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void obtenerTest(){
        try {
            Usuario u = usuarioServicio.obtenerUsuario(123);
            Assertions.assertNotNull(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest(){
        Usuario usuario = null;
        try {
            usuario = usuarioServicio.iniciarSesion("pepito@email.com", "1235");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false, e.getMessage());
        }

    }
}
