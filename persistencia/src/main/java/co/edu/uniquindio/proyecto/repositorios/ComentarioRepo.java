package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    @Query("SELECT c FROM Comentario c WHERE c.calificacion > :calificacionMenor AND c.calificacion < :calificacionMayor")
    List<Comentario> listarComentariosRango(int calificacionMenor, int calificacionMayor);
}
