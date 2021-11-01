package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void obtenerProducto(){
        Usuario vendedor = null;
        try {
            vendedor = usuarioServicio.obtenerUsuario(123);
            LocalDateTime ldt = LocalDateTime.of(2021, 12, 25, 20, 10);
            Producto producto = new Producto("Tv 55 " , "Maravilloso Tv", 10, "Bueno", 2000.000, ldt, 0.0, vendedor);
            Producto publicado = productoServicio.publicarProducto(producto);
            Assertions.assertNotNull(publicado);

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false, e.getMessage());
        }

    }
}
