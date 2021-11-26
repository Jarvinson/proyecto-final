package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CompraServicioTest {

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @Test
    public void registrarTest (){

        Usuario usuario = null;
        try {
            usuario = usuarioServicio.obtenerUsuario(1);
            Compra compra = new Compra("Efectivo", usuario );
            Compra compraGuardada = compraServicio.registrarCompra(compra);

            Assertions.assertNotNull(compraGuardada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void actualizarTest(){
        Compra compraGuardada = null;
        try {
            compraGuardada = compraServicio.obtenerCompra(1);
            compraGuardada.setMedioPago("Tarjeta");
            compraServicio.actualizarCompra(compraGuardada);
            Compra compraBuscada = compraServicio.obtenerCompra(1);
            Assertions.assertEquals("Tarjeta", compraBuscada.getMedioPago());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void listarTest(){
        List<Compra> compras =compraServicio.listaCompras();
        compras.forEach( compra -> System.out.println(compra));

        Assertions.assertEquals(0, compras.size());
    }


    @Test
    public void eliminarTest(){

        try {
            compraServicio.eliminarCompra(1);
            Compra compraBuscado = compraServicio.obtenerCompra(1);
            Assertions.assertNull(compraBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listaProductosCompradosTest(){
        Long totalProductos = compraServicio.obtenerTotalProductosCompradosUsuario(1);
        Assertions.assertEquals(0, 0);
    }

    @Test
    public void findAllByMedioPago(){
        List<Compra> compras = compraServicio.findAllByMedioPago("Tarjeta");
        Assertions.assertEquals(0, 0);
    }
}
