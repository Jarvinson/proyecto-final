package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {
    @Autowired //Etiqueta que permite instanciar automáticamente el objeto creado
    private AdministradorRepo administradorRepo;
    @Autowired
    private CiudadRepo ciudadRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de Administradores
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){

        Administrador administrador = new Administrador(1, "Jarvinson Valencia", "jarvinsonvalencia@gmail.com", "jarvinson123");
        Administrador administradorGuardado = administradorRepo.save(administrador);
        Assertions.assertNotNull(administradorGuardado);

    }

    //Función que permite realizar las pruebas unitarias para la modificación de administradores
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        //Obtenemos el objeto que vamos a modificar mendiante su ID
        Administrador administradorGuardado = administradorRepo.findById(1).orElse(null);

        //Seteamos el nuevo valor para el campo que se va a modificar
        administradorGuardado.setEmail("jvalencia@gmail.com");

        //Guardamos el objeto con el cambio aplicado
        administradorRepo.save(administradorGuardado);

        Administrador administradorBuscado = administradorRepo.findById(1).orElse(null);
        Assertions.assertEquals("jvalencia@gmail.com", administradorBuscado.getEmail());
    }

    //Función que permite realizar las pruebas unitarias para el listado de administradors
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Administrador> administradores =administradorRepo.findAll();
        administradores.forEach( administrador -> System.out.println(administrador));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de administradores
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        administradorRepo.deleteById(1);
        Administrador administradorBuscado = administradorRepo.findById(1).orElse(null);
        Assertions.assertNull(administradorBuscado);
    }

    // //Función que permite realizar las pruebas unitarias para filtrar el nombre de los Administradores
    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){

        List<Administrador> lista = administradorRepo.findAllByNombreContains("Daniela");
        lista.forEach(u-> System.out.println(u));
    }

    //Función que permite realizar las pruebas unitarias para filtrar los administradores por email.
    @Test
    @Sql("classpath:data.sql")
    public void filtrarEmailTest(){

        Optional<Administrador> administrador = administradorRepo.findByEmail("danielavilsdsdsflegas@gmail.com");

        if (administrador.isPresent()){
            System.out.println(administrador.get());
        }else{
            System.out.println("NO existe este correo");
        }
    }

    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Administrador> lista = administradorRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Administrador> lista = administradorRepo.findAll(Sort.by("nombre"));

        System.out.println(lista.stream().collect(Collectors.toList()));
       // System.out.println(lista);
    }
}
