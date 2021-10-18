package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaTest {
    @Autowired
    private SubastaRepo subastaRepo;
    
    @Autowired
    private ProductoRepo productoRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de subastas
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){
       
        Producto producto1 = productoRepo.findById(103).orElse(null);
        Subasta subasta= new Subasta(310, LocalDate.now(), producto1);
        Subasta subastaGuardada = subastaRepo.save(subasta);

        Assertions.assertNotNull(subastaGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de subastas
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        Subasta subastaGuardada = subastaRepo.findById(301).orElse(null);
        subastaGuardada.setCodigo(320);

        subastaRepo.save(subastaGuardada);

        Subasta chatBuscada = subastaRepo.findById(301).orElse(null);
        Assertions.assertEquals(320, chatBuscada.getCodigo());
    }

    //Función que permite realizar las pruebas unitarias para el listado de subastas
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Subasta> subastas =subastaRepo.findAll();
        subastas.forEach( subasta-> System.out.println(subasta));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de subastas
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        subastaRepo.deleteById(303);
        Subasta chatBuscado = subastaRepo.findById(303).orElse(null);
        Assertions.assertNull(chatBuscado);
    }



    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Subasta> lista = subastaRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));

    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Subasta> lista = subastaRepo.findAll(Sort.by("codigo"));

        System.out.println(lista.stream().collect(Collectors.toList()));

    }
}
