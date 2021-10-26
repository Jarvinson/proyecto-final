package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
// Repositorio de la clase Ciudad
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    List<Ciudad> findAllByNombreContains(String nombre);
    Page<Ciudad> findAll(Pageable paginador);

    @Query("select u from Ciudad c join c.usuario u where c.nombre = :nombre")
    List<Usuario> listarUsarios (String nombre);
}
