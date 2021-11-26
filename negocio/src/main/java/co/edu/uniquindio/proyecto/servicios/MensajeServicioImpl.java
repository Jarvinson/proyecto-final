package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeServicioImpl implements MensajeServicio{

    @Autowired
    private MensajeRepo mensajeRepo;

    @Override
    public Mensaje registrarMensaje(Mensaje m) throws Exception {
        return mensajeRepo.save(m);
    }

    @Override
    public Mensaje actualizarMensaje(Mensaje m) throws Exception {
        Optional<Mensaje> buscado = mensajeRepo.findById(m.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("El mensaje no existe");
        }
        return mensajeRepo.save(m);
    }

    @Override
    public void eliminarMensaje(Integer id) throws Exception {
        Optional<Mensaje> buscado = mensajeRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("El mensaje no existe");
        }
        mensajeRepo.deleteById(id);
    }

    @Override
    public Mensaje obtenerMensaje(Integer id) throws Exception {
        return mensajeRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ningún mensaje"));
    }

    @Override
    public List<Mensaje> listarMensajes() {
        return mensajeRepo.findAll();
    }
}
