package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServicioImpl implements ChatServicio{

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public Chat registrarChat(Chat c) throws Exception {
        return chatRepo.save(c);
    }

    @Override
    public Chat actualizarChat(Chat c) throws Exception {
        Optional<Chat> buscado = chatRepo.findById(c.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("El chat no existe");
        }
        return chatRepo.save(c);
    }

    @Override
    public void eliminarChat(Integer id) throws Exception {
        Optional<Chat> buscado = chatRepo.findById(id);
        if ( buscado.isEmpty()){
            throw new Exception("El chat no existe");
        }
        chatRepo.deleteById(id);
    }

    @Override
    public Chat obtenerChat(Integer id) throws Exception {
        return chatRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ningún chat"));
    }

    @Override
    public List<Chat> listarChats() {
        return chatRepo.findAll();
    }
}
