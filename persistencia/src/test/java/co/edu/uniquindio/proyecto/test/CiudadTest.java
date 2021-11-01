package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
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

//Esta clase contiene los métodos para realizar las pruebas unitarias al objeto Ciudad
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de Ciudades
    @Test
    public void registrarTest (){
        Ciudad ciudad = new Ciudad("Medellin");
        Ciudad ciudadGuardada = ciudadRepo.save(ciudad);

        Assertions.assertNotNull(ciudadGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de Ciudades
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        Ciudad ciudadGuardada = ciudadRepo.findById(1).orElse(null);
        ciudadGuardada.setNombre("Medellin");

        ciudadRepo.save(ciudadGuardada);

        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);
        Assertions.assertEquals("Medellin", ciudadBuscada.getNombre());
    }

    //Función que permite realizar las pruebas unitarias para el listado de ciudades
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Ciudad> ciudades =ciudadRepo.findAll();
        ciudades.forEach( ciudad -> System.out.println(ciudad));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de Ciudades
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        ciudadRepo.deleteById(2);
        Ciudad ciudadBuscado = ciudadRepo.findById(2).orElse(null);
        Assertions.assertNull(ciudadBuscado);
    }

    //Función que permite realizar las pruebas unitarias para filtrar el nombre de las ciudades
    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){

        List<Ciudad> lista = ciudadRepo.findAllByNombreContains("Armenia");
        lista.forEach(u-> System.out.println(u));
    }

    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Ciudad> lista = ciudadRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Ciudad> lista = ciudadRepo.findAll(Sort.by("nombre"));

        System.out.println(lista.stream().collect(Collectors.toList()));
       //System.out.println(lista);
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarUsuariosCiudadTest(){
        List <Usuario> listarUsuario = ciudadRepo.listarUsarios("Armenia");
       // listarUsuario.forEach(System.out::println);

        Assertions.assertEquals(2, listarUsuario.size());
    }
}
