package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaUsuarioTest {

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de subastas
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){

        Usuario usuario = usuarioRepo.findById(1).orElse(null);
        Subasta subasta = subastaRepo.findById(300).orElse(null);
        SubastaUsuario subastaUsuario= new SubastaUsuario(10, 100.000, LocalDate.now(), usuario, subasta);
        SubastaUsuario subastaGuardada = subastaUsuarioRepo.save(subastaUsuario);

        Assertions.assertNotNull(subastaGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de subastas
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        SubastaUsuario subastaGuardada = subastaUsuarioRepo.findById(2).orElse(null);
        subastaGuardada.setCodigo(10);

        subastaUsuarioRepo.save(subastaGuardada);

        SubastaUsuario subastaUsuario = subastaUsuarioRepo.findById(2).orElse(null);
        Assertions.assertEquals(10, subastaUsuario.getCodigo());
    }

    //Función que permite realizar las pruebas unitarias para el listado de subastas
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<SubastaUsuario> subastas =subastaUsuarioRepo.findAll();
        subastas.forEach( subasta-> System.out.println(subasta));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de subastas
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        subastaUsuarioRepo.deleteById(1);
        SubastaUsuario chatBuscado = subastaUsuarioRepo.findById(1).orElse(null);
        Assertions.assertNull(chatBuscado);
    }



    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<SubastaUsuario> lista = subastaUsuarioRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));

    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<SubastaUsuario> lista = subastaUsuarioRepo.findAll(Sort.by("codigo"));

        System.out.println(lista.stream().collect(Collectors.toList()));
    }
}
