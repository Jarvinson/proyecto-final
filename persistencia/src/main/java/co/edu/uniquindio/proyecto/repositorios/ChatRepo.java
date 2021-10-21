package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Repositorio de la clase Chat
public interface ChatRepo extends JpaRepository<Chat,Integer> {

    Page<Chat> findAll(Pageable paginador);
    //List<Chat> findAllByMensajescontains(String mensaje);
}
