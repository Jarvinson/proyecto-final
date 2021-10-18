package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    List<Ciudad> findAllByNombreContains(String nombre);
    Page<Ciudad> findAll(Pageable paginador);
}
