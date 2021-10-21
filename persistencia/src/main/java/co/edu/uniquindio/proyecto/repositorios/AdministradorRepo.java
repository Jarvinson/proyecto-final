package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// Repositorio de la clase Administrador
public interface AdministradorRepo extends JpaRepository<Administrador, Integer> {

    List<Administrador> findAllByNombreContains(String nombre);

    Optional<Administrador> findByEmail(String email);

    Optional<Administrador> findByEmailAndPassword(String email, String password);

    Page<Administrador> findAll(Pageable paginador);
}
