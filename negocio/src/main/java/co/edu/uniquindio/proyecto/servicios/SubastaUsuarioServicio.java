package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;

import java.util.List;

public interface SubastaUsuarioServicio {

    SubastaUsuario registrarSubastaUsuario(SubastaUsuario su) throws Exception;

    SubastaUsuario actualizarSubastaUsuario(SubastaUsuario su) throws Exception;

    SubastaUsuario obtenerSubastaUsuario(Integer id) throws Exception;

    void eliminarSubastaUsuario(Integer id) throws Exception;

    List<SubastaUsuario> listarSubastaUsuario();



}
