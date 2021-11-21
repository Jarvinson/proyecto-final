package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface CiudadServicio {

    List<Ciudad> listarCiudades();

    Ciudad obtenerCiudad(Integer id) throws Exception;

    Ciudad registrarCiudad(Ciudad c) throws Exception;

    Ciudad actualizarCiudad(Ciudad c) throws Exception;

    void eliminarCiudad(Integer id) throws Exception;

    List<Usuario> listarUsuarioCiudades(String ciudad) throws Exception;


}
