package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private CiudadRepo ciudadRepo;


    @Test
    public void registrarTest (){

        Administrador administrador = new Administrador(1, "Jarvinson Valencia", "jarvinsonvalencia@gmail.com", "jarvinson123");
        Administrador administradorGuardado = null;
        try {
            administradorGuardado = administradorServicio.registrarAdministrador(administrador);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(administradorGuardado);

    }


    @Test
    public void actualizarTest(){
        Administrador administrador = null;
        try {
            administrador = administradorServicio.obtenerAdministrador(1);
            administrador.setCodigo(12);
            administradorServicio.actualizarAdministrador(administrador);
            Administrador obtener = administradorServicio.obtenerAdministrador(1);

            Assertions.assertEquals(12, obtener.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void listarTest(){
        List<Administrador> administradores =administradorServicio.listarAdmnistradores();
        administradores.forEach( administrador -> System.out.println(administrador));
        Assertions.assertEquals(0, administradores.size());
    }


    @Test
    public void eliminarTest(){

        try {
            administradorServicio.eliminarAdministrador(1);
            Administrador administradorBuscado = administradorServicio.obtenerAdministrador(1);
            Assertions.assertNull(administradorBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void filtrarEmailTest(){
        Optional<Administrador> administrador = null;
        try {
            administrador = administradorServicio.filtrarAdministradorEmail("pepito@email.com");
            Assertions.assertNotNull(administrador);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
