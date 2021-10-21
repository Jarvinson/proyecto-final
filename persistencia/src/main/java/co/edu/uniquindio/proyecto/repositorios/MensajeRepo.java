package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Repositorio de la clase  Mensaje
public interface MensajeRepo extends JpaRepository<Mensaje, Integer> {

    Page<Mensaje> findAll(Pageable paginador);
}
