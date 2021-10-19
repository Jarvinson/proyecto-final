package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
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
import java.util.*;
import java.util.stream.Collectors;

//Esta clase contiene los métodos para realizar las pruebas unitarias al objeto Producto
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;


    //Función que permite realizar las pruebas unitarias para la de creación de Productos
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){
        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        Usuario usuario = usuarioRepo.findById(1).orElse(null);
        List<Usuario> usuarioList = usuarioRepo.findAll();
        List<Categoria> categoriaList = categoriaRepo.findAll();
        Map<String, String> imagenes = new HashMap<>();


        Producto producto = new Producto(100, "Six Pack Poker", 50, "Caja por 6 cervezas", 16.500, LocalDate.now(), 0.0, usuario, usuarioList, categoriaList,ciudad, imagenes);
        Producto productoGuardado = productoRepo.save(producto);
        Assertions.assertNotNull(productoGuardado);

    }

    //Función que permite realizar las pruebas unitarias para la modificación de productos
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        //Obtenemos el objeto que vamos a modificar mediante su ID
        Producto productoGuardado = productoRepo.findById(100).orElse(null);

        //Seteamos el nuevo valor para el campo que se va a modificar
        productoGuardado.setPrecio(10.000);

        //Guardamos el objeto con el cambio aplicado
        productoRepo.save(productoGuardado);

        Producto productoBuscado = productoRepo.findById(100).orElse(null);
        Assertions.assertEquals(10.000, productoBuscado.getPrecio());
    }

    //Función que permite realizar las pruebas unitarias para el listado de productos
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Producto> productos =productoRepo.findAll();
        productos.forEach( producto -> System.out.println(producto));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de productos
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        productoRepo.deleteById(100);
        Producto productoBuscado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(productoBuscado);
    }

    //Función que permite realizar las pruebas unitarias para el filtrado por nombre
    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){

        List<Producto> lista = productoRepo.findAllByNombreContains("Pro");
        lista.forEach(u-> System.out.println(u));
    }

    //Función que permite realizar las pruebas unitarias para controlar el cargado datos de los productos
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Producto> lista = productoRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar un listado de productos
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Producto> lista = productoRepo.findAll(Sort.by("nombre"));

        // System.out.println(lista.stream().collect(Collectors.toList()));
        System.out.println(lista);
    }
}
