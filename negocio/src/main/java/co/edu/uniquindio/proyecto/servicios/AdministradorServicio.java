package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

import java.util.List;
import java.util.Optional;

public interface AdministradorServicio {

    Administrador registrarAdministrador(Administrador a) throws Exception;

    Administrador actualizarAdministrador(Administrador a) throws Exception;

    void eliminarAdministrador(Integer id) throws Exception;

    List<Administrador> listarAdmnistradores();

    Administrador obtenerAdministrador(Integer id) throws Exception;

    Optional<Administrador> filtrarAdministradorEmail(String email) throws Exception;

}
