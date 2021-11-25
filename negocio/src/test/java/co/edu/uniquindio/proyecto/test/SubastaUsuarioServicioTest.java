package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
import co.edu.uniquindio.proyecto.servicios.SubastaUsuarioServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class SubastaUsuarioServicioTest {
    @Autowired
    private SubastaUsuarioServicio subastaUsuarioServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private SubastaServicio subastaServicio;

    @Test
    public void registrarTest (){

        Usuario usuario = null;
        try {
            usuario = usuarioServicio.obtenerUsuario(123);
            Subasta subasta = subastaServicio.obtenerSubasta(1);
            SubastaUsuario subastaUsuario= new SubastaUsuario(100.000, LocalDate.now(), usuario, subasta);
            SubastaUsuario subastaGuardada = subastaUsuarioServicio.registrarSubastaUsuario(subastaUsuario);

            Assertions.assertNotNull(subastaGuardada);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void actualizarTest(){
        SubastaUsuario subastaGuardada = null;
        try {
            subastaGuardada = subastaUsuarioServicio.obtenerSubastaUsuario(1);
            subastaGuardada.setCodigo(10);
            subastaUsuarioServicio.actualizarSubastaUsuario(subastaGuardada);
            SubastaUsuario subastaUsuario = subastaUsuarioServicio.obtenerSubastaUsuario(1);
            Assertions.assertEquals(10, subastaUsuario.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listarTest(){
        List<SubastaUsuario> subastas =subastaUsuarioServicio.listarSubastaUsuario();
        subastas.forEach( subasta-> System.out.println(subasta));
        Assertions.assertEquals(0, subastas.size());
    }

    @Test
    public void eliminarTest(){
        try {
            subastaUsuarioServicio.eliminarSubastaUsuario(1);
            SubastaUsuario buscado = subastaUsuarioServicio.obtenerSubastaUsuario(1);
            Assertions.assertNull(buscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void obtenerTest(){
        SubastaUsuario subastaUsuario = null;
        try {
            subastaUsuario = subastaUsuarioServicio.obtenerSubastaUsuario(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(subastaUsuario);
    }
}
