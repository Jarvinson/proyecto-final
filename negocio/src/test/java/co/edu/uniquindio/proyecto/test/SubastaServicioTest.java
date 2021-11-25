package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class SubastaServicioTest {

    @Autowired
    private SubastaServicio subastaServicio;

    @Autowired
    private ProductoServicio productoServicio;


    @Test
    public void registrarTest (){

        Producto producto1 = null;
        try {
            producto1 = productoServicio.obtenerProducto(1);
            Subasta subasta= new Subasta(LocalDate.now(), producto1);
            Subasta subastaGuardada = subastaServicio.registrarSubasta(subasta);

            Assertions.assertNotNull(subastaGuardada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void actualizarTest(){
        Subasta subastaGuardada = null;
        try {
            subastaGuardada = subastaServicio.obtenerSubasta(1);
            subastaGuardada.setCodigo(320);
            subastaServicio.actualizarSubasta(subastaGuardada);
            Subasta buscado = subastaServicio.obtenerSubasta(1);
            Assertions.assertEquals(320, buscado.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void listarTest(){
        List<Subasta> subastas =subastaServicio.listarSubastas();
        subastas.forEach( subasta-> System.out.println(subasta));

        Assertions.assertEquals(0, subastas.size());
    }

    @Test
    public void eliminarTest(){

        try {
            subastaServicio.eliminarSubasta(1);
            Subasta chatBuscado = subastaServicio.obtenerSubasta(1);
            Assertions.assertNull(chatBuscado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void obtenerTest(){
        try {
            Subasta subasta = subastaServicio.obtenerSubasta(1);
            Assertions.assertNotNull(subasta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subastaDisponibleTest(){
       List<Subasta> disponibles = subastaServicio.listarSubastasDisponibles();
       Assertions.assertEquals(0, disponibles.size());
    }

}
