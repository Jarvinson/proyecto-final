package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
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
public class CiudadServicioTest {
    @Autowired
    private CiudadServicio ciudadServicio;


    @Test
    public void registrarTest (){
        Ciudad ciudad = new Ciudad("Medellin");
        Ciudad ciudadGuardada = null;
        try {
            ciudadGuardada = ciudadServicio.registrarCiudad(ciudad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(ciudadGuardada);
    }

    @Test
    public void actualizarTest(){
        Ciudad ciudadGuardada = null;
        try {
            ciudadGuardada = ciudadServicio.obtenerCiudad(1);
            ciudadGuardada.setNombre("Popayan");
            ciudadServicio.actualizarCiudad(ciudadGuardada);
            Ciudad ciudadBuscada = ciudadServicio.obtenerCiudad(1);
            Assertions.assertEquals("Popayan", ciudadBuscada.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listarTest(){
        List<Ciudad> ciudades =ciudadServicio.listarCiudades();
        ciudades.forEach( ciudad -> System.out.println(ciudad));
        Assertions.assertEquals(5, ciudades.size());
    }

    @Test
    public void eliminarTest(){

        try {
            ciudadServicio.eliminarCiudad(2);
            Ciudad ciudadBuscado = ciudadServicio.obtenerCiudad(2);
            Assertions.assertNull(ciudadBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void listarUsuariosCiudadTest(){
        List <Usuario> listarUsuario = null;
        try {
            listarUsuario = ciudadServicio.listarUsuarioCiudades("Armenia");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(1, listarUsuario.size());
    }
}
