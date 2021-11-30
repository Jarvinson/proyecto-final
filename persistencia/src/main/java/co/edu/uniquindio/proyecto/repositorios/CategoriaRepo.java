package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// Repositorio de la clase  Categoria
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    List<Categoria> findAllByNombreContains(String nombre);

    Page<Categoria> findAll(Pageable paginador);

    @Query("select c, count(p) as total from Producto p join p.categoria c group by c order by total desc")
    List<Object[]> obtenerCategoriaMasUsada ();
}
