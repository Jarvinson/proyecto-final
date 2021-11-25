package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Chat;

import java.util.List;

public interface ChatServicio {

    Chat registrarChat(Chat c) throws Exception;

    Chat actualizarChat(Chat c) throws Exception;

    void eliminarChat(Integer id) throws Exception;

    Chat obtenerChat(Integer id) throws Exception;

    List<Chat> listarChats();
}
