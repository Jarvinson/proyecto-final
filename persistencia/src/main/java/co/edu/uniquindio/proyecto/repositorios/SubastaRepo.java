package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Repositorio de la clase Subasta
public interface SubastaRepo extends JpaRepository<Subasta, Integer> {
    Page<Subasta> findAll(Pageable paginador);

    @Query("select max(d.valor) from Subasta s join s.subastaUsuario d where s.codigo = :codigo")
    Float obtenerValorMasAlto(Integer codigo);

    @Query("select s from Subasta s where  current_timestamp < s.fechaLimite")
    List<Subasta> listarSubastasDisponibles();
}
