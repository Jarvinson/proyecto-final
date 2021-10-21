package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
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

//Esta clase contiene los métodos para realizar las pruebas unitarias al objeto DetalleCompra
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;
    
    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private CompraRepo compraRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de Detalles Compra
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){
       
        Producto producto1 = productoRepo.findById(103).orElse(null);
        Compra  compra = compraRepo.findById(2).orElse(null);
        DetalleCompra detalleCompra= new DetalleCompra(410,5, 34000.0,compra, producto1);
        DetalleCompra subastaGuardada = detalleCompraRepo.save(detalleCompra);

        Assertions.assertNotNull(subastaGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de Detalles Compra
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        DetalleCompra subastaGuardada = detalleCompraRepo.findById(401).orElse(null);
        subastaGuardada.setCodigo(420);

        detalleCompraRepo.save(subastaGuardada);

        DetalleCompra chatBuscada = detalleCompraRepo.findById(401).orElse(null);
        Assertions.assertEquals(420, chatBuscada.getCodigo());
    }

    //Función que permite realizar las pruebas unitarias para el listado de Detalles Compra
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<DetalleCompra> DetallesCompra =detalleCompraRepo.findAll();
        DetallesCompra.forEach( detalleCompra-> System.out.println(detalleCompra));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de Detalles Compra
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        detalleCompraRepo.deleteById(403);
        DetalleCompra chatBuscado = detalleCompraRepo.findById(303).orElse(null);
        Assertions.assertNull(chatBuscado);
    }



    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<DetalleCompra> lista = detalleCompraRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));

    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<DetalleCompra> lista = detalleCompraRepo.findAll(Sort.by("codigo"));

        System.out.println(lista.stream().collect(Collectors.toList()));

    }
}
