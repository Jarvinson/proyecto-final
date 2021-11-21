package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Test
    public void obtenerProductoTest(){
        Usuario vendedor = null;
        try {
            vendedor = usuarioServicio.obtenerUsuario(123);
            LocalDateTime ldt = LocalDateTime.of(2021, 12, 25, 20, 10);
            Producto producto = new Producto("Tv 55 " , "Maravilloso Tv", 10, "Bueno", 2000.000,  0.0, ldt, vendedor);
            Producto publicado = productoServicio.publicarProducto(producto);
            Assertions.assertNotNull(publicado);

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false, e.getMessage());
        }

    }

    @Test
    public void actualizarProductoTest(){
        try {
            Producto producto = productoServicio.obtenerProducto(1);
            producto.setPrecio(150.000);
            productoServicio.actualizarProducto(producto);

            Producto productoBuscado = productoServicio.obtenerProducto(1);
            Assertions.assertEquals(150.000, productoBuscado.getPrecio());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void eliminarTest(){

        try {
            productoServicio.eliminarProducto(2);
            Producto producto = productoServicio.obtenerProducto(2);

            Assertions.assertNull(producto);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void listarTodosProductosTest(){
        List<Producto> respuesta = productoServicio.listarTodosProductos();
        respuesta.forEach(objeto -> System.out.println(objeto));
        Assertions.assertEquals(3, respuesta.size());
    }

    @Test
    public void listarPorCategoriaTest(){
        Categoria categoria = null;
        try {
            categoria = categoriaServicio.obtenerCategoria(2);
            List<Producto> respuesta = productoServicio.listarProductos(categoria);
            respuesta.forEach(objeto -> System.out.println(objeto));
            Assertions.assertEquals(2, respuesta.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void comentarProductoTest(){
        Usuario usuario = null;
        Producto producto = null;
        try {
            usuario = usuarioServicio.obtenerUsuario(123);
            producto = productoServicio.obtenerProducto(1);
            Comentario comentario = new Comentario("Good!", null, 5, producto, usuario);
            productoServicio.comentarProducto(comentario);
            Assertions.assertNotNull(comentario);

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    public void buscarProductoNombreTest(){
        List<Producto> productos = null;
        try {
            productos = productoServicio.buscarProductos("Televisor", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productos.forEach(System.out::println);
        Assertions.assertEquals(1, productos.size());
    }

    @Test
    public void listarProductosUsuarioTest(){
        try {
            List<Producto> productos = productoServicio.listarProductosUsuario(123);
            Assertions.assertEquals(0, productos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
