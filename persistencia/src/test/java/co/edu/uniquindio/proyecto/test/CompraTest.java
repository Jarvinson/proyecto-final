package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de compra
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){

        Usuario usuario = usuarioRepo.findById(1).orElse(null);
        Compra compra = new Compra(1, LocalDateTime.now(), "Efectivo", usuario );
        Compra compraGuardada = compraRepo.save(compra);

        Assertions.assertNotNull(compraGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de compra
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        Compra compraGuardada = compraRepo.findById(2).orElse(null);
        compraGuardada.setMedioPago("Tarjeta");

        compraRepo.save(compraGuardada);

        Compra compraBuscada = compraRepo.findById(2).orElse(null);
        Assertions.assertEquals("Tarjeta", compraBuscada.getMedioPago());
    }

    //Función que permite realizar las pruebas unitarias para el listado de compra
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Compra> compras =compraRepo.findAll();
        compras.forEach( compra -> System.out.println(compra));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de compra
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        compraRepo.deleteById(3);
        Compra compraBuscado = compraRepo.findById(3).orElse(null);
        Assertions.assertNull(compraBuscado);
    }

    //Función que permite realizar las pruebas unitarias para filtrar el medio de pago de las compras
    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){

        List<Compra> lista = compraRepo.findAllByMedioPago("Efectivo");
        lista.forEach(u-> System.out.println(u));
    }

    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Compra> lista = compraRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Compra> lista = compraRepo.findAll(Sort.by("medioPago"));

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }
}
