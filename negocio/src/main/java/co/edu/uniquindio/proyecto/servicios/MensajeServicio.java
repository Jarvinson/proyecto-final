package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Mensaje;

import java.util.List;

public interface MensajeServicio {

    Mensaje registrarMensaje(Mensaje m) throws Exception;

    Mensaje actualizarMensaje(Mensaje m) throws Exception;

    void eliminarMensaje(Integer id) throws Exception;

    Mensaje obtenerMensaje(Integer id) throws Exception;

    List<Mensaje> listarMensajes();
}
