package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.DetalleCompraServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class DetalleCompraServicioTest {

    @Autowired
    private DetalleCompraServicio detalleCompraServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CompraServicio compraServicio;


    @Test
    public void registrarTest (){

        Producto producto1 = null;
        try {
            producto1 = productoServicio.obtenerProducto(1);
            Compra compra = compraServicio.obtenerCompra(1);
            DetalleCompra detalleCompra= new DetalleCompra(5, 34000.0,compra, producto1);
            DetalleCompra subastaGuardada = detalleCompraServicio.registrarDetalleCompra(detalleCompra);

            Assertions.assertNotNull(subastaGuardada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void actualizarTest(){
        DetalleCompra detalleCompra = null;
        try {
            detalleCompra = detalleCompraServicio.obtenerDetalleCompra(1);
            detalleCompra.setCodigo(420);
            detalleCompraServicio.actualizarDetalleCompra(detalleCompra);
            DetalleCompra detalleBuscado = detalleCompraServicio.obtenerDetalleCompra(1);
            Assertions.assertEquals(420, detalleBuscado.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void listarTest(){
        List<DetalleCompra> detallesCompra =detalleCompraServicio.listarDetalleCompra();
        detallesCompra.forEach( detalleCompra-> System.out.println(detalleCompra));

        Assertions.assertEquals(0, detallesCompra.size() );
    }


    @Test
    public void eliminarTest(){

        try {
            detalleCompraServicio.eliminarDetalleCompra(1);
            DetalleCompra chatBuscado = detalleCompraServicio.obtenerDetalleCompra(1);
            Assertions.assertNull(chatBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void obtenerTest(){
        try {
            DetalleCompra detalleCompra = detalleCompraServicio.obtenerDetalleCompra(1);
            Assertions.assertNotNull(detalleCompra);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
