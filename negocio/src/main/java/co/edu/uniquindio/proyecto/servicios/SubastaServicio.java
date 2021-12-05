package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface SubastaServicio {

    Subasta registrarSubasta(Subasta s) throws Exception;

    Subasta actualizarSubasta(Subasta s) throws Exception;

    Subasta obtenerSubasta(Integer id) throws Exception;

    void eliminarSubasta(Integer id) throws Exception;

    List<Subasta> listarSubastas();

    Float obtenerValorMasAlto(Integer codigo);

    List<Subasta> listarSubastasDisponibles();

    Subasta crearSubasta(LocalDate fechaLimite, Producto producto, Usuario usuario, double valor, LocalDate fechaSubasta) throws Exception;
}
