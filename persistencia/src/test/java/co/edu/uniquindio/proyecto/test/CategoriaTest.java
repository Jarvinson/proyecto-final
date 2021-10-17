package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {
    @Autowired
    private CategoriaRepo categoriaRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de categorías
    @Test
    public void registrarTest (){
        Categoria categoria = new Categoria(1, "Bebidas y alimentos");
        Categoria categoriaGuardada = categoriaRepo.save(categoria);

        Assertions.assertNotNull(categoriaGuardada);
    }

    //Función que permite realizar las pruebas unitarias para la modificación de categorías
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        Categoria categoriaGuardada = categoriaRepo.findById(2).orElse(null);
        categoriaGuardada.setNombre("Fiesta");

        categoriaRepo.save(categoriaGuardada);

        Categoria categoriaBuscada = categoriaRepo.findById(2).orElse(null);
        Assertions.assertEquals("Fiesta", categoriaBuscada.getNombre());
    }

    //Función que permite realizar las pruebas unitarias para el listado de categorías
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Categoria> categorias =categoriaRepo.findAll();
        categorias.forEach( categoria -> System.out.println(categoria));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de categorías
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        categoriaRepo.deleteById(3);
        Categoria categoriaBuscado = categoriaRepo.findById(3).orElse(null);
        Assertions.assertNull(categoriaBuscado);
    }

    //Función que permite realizar las pruebas unitarias para filtrar el nombre de las categorías
    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){

        List<Categoria> lista = categoriaRepo.findAllByNombreContains("Be");
        lista.forEach(u-> System.out.println(u));
    }

    //Función que permite realizar las pruebas unitarias de un paginador
    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Categoria> lista = categoriaRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar una lista
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Categoria> lista = categoriaRepo.findAll(Sort.by("nombre"));

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }
}
