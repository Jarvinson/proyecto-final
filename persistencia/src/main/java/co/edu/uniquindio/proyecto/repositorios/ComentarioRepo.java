package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Repositorio de la clase Comentario
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    @Query("SELECT c FROM Comentario c WHERE c.calificacion > :calificacionMenor AND c.calificacion < :calificacionMayor")
    List<Comentario> listarComentariosRango(int calificacionMenor, int calificacionMayor);

    @Query("select avg(c.calificacion) from Comentario c where c.producto.codigo = :codigo")
    Integer calidicacionPromedio(Integer codigo);

    List<Comentario> findAllByMensajeContains(String nombre);

    Page<Comentario> findAll(Pageable paginador);
}
