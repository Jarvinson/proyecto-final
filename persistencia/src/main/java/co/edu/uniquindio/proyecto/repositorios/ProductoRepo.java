package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// Repositorio de la clase Producto
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    List<Producto> findAllByNombreContains(String nombre);

    Page<Producto> findAll(Pageable paginador);
}
