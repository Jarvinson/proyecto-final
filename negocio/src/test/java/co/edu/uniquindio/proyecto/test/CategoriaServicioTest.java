package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CategoriaServicioTest {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Test
    public void listarCategoriasTest(){
        List<Categoria> categorias = categoriaServicio.listarCategorias();
        Assertions.assertEquals(3, categorias.size());
    }

    @Test
    public void obtenerCategoriaTest(){
        try {
            Categoria categoria = categoriaServicio.obtenerCategoria(1);
            Assertions.assertNotNull(categoria);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registrarTest(){

        Categoria categoriaGuardada = null;
        try {
            Categoria categoria = new Categoria(5, "Bebidas y alimentos");
            categoriaGuardada = categoriaServicio.registrarCategoria(categoria);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertNotNull(categoriaGuardada);
    }

    @Test
    public void actualizarTest(){
        try {
            Categoria categoria = categoriaServicio.obtenerCategoria(1);
            categoria.setNombre("Fiesta");
            Categoria categoriaGuardada = categoriaServicio.actualizarCategoria(categoria);
            Categoria categoriaBuscada = categoriaServicio.obtenerCategoria(1);
            Assertions.assertEquals("Fiesta", categoriaBuscada.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarTest(){

        try {
            categoriaServicio.eliminarCategoria(5);
            Categoria categoriaBuscado = categoriaServicio.obtenerCategoria(5);
            Assertions.assertNull(categoriaBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void filtrarNombreTest(){

        List<Categoria> lista = null;
        try {
            lista = categoriaServicio.filtrarNombreCategoria("Tec");
        } catch (Exception e) {
            e.printStackTrace();
        }
        lista.forEach(u-> System.out.println(u));

        Assertions.assertEquals(1, lista.size());
    }




}
