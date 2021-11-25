package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubastaUsuarioServcioImpl implements SubastaUsuarioServicio {

    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    @Override
    public SubastaUsuario registrarSubastaUsuario(SubastaUsuario su) throws Exception {
        Optional<SubastaUsuario> buscado = subastaUsuarioRepo.findById(su.getCodigo());

        if ( buscado.isPresent()){
            throw new Exception("El código de la subasta usuario ya existe");
        }
        return subastaUsuarioRepo.save(su);
    }

    @Override
    public SubastaUsuario actualizarSubastaUsuario(SubastaUsuario su) throws Exception {
        Optional<SubastaUsuario> buscado = subastaUsuarioRepo.findById(su.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("El código de la subasta usuario no existe");
        }
        return subastaUsuarioRepo.save(su);
    }

    @Override
    public SubastaUsuario obtenerSubastaUsuario(Integer id) throws Exception {
        return subastaUsuarioRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ninguna subasta usaurio"));
    }

    @Override
    public void eliminarSubastaUsuario(Integer id) throws Exception {
        Optional<SubastaUsuario> buscado = subastaUsuarioRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("El código de la subasta usuario no existe");
        }
        subastaUsuarioRepo.deleteById(id);
    }

    @Override
    public List<SubastaUsuario> listarSubastaUsuario() {
        return subastaUsuarioRepo.findAll();
    }
}
