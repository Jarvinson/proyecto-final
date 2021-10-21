package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Repositorio de la clase SubastaUsuario
public interface SubastaUsuarioRepo extends JpaRepository<SubastaUsuario, Integer> {

    Page<SubastaUsuario> findAll(Pageable paginador);
}
