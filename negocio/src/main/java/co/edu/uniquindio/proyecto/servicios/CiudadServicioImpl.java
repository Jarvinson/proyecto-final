package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicioImpl implements CiudadServicio{

    @Autowired
    private CiudadRepo ciudadRepo;

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Ciudad obtenerCiudad(Integer id) throws Exception {
        return ciudadRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ninguna ciudad"));
    }

    @Override
    public Ciudad registrarCiudad(Ciudad c) throws Exception {
        return ciudadRepo.save(c);
    }

    @Override
    public Ciudad actualizarCiudad(Ciudad c) throws Exception {
        Optional<Ciudad> buscado = ciudadRepo.findById(c.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("La ciudad no existe");
        }
        return ciudadRepo.save(c);
    }

    @Override
    public void eliminarCiudad(Integer id) throws Exception {
        Optional<Ciudad> buscado = ciudadRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("La ciudad no existe");
        }
        ciudadRepo.deleteById(id);
    }

    @Override
    public List<Usuario> listarUsuarioCiudades(String ciudad) throws Exception {
        return ciudadRepo.listarUsarios(ciudad);
    }


}
