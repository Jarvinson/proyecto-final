package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
// Repositorio de la clase Producto
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    List<Producto> findAllByNombreContains(String nombre);

    Page<Producto> findAll(Pageable paginador);

    @Query("select p.vendedor.nombre from Producto p where p.codigo = :codigo")
    String obtenerNombreVendedor(Integer codigo);

    @Query("select p, c from Producto  p left join p.comentarioList c")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.usuario from Producto  p join p.comentarioList c where p.codigo = :id")
    List<Usuario> listarUsuariosComentarios(Integer id);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido (p.nombre, p.descripcion, p.precio, p.ciudad)  from Producto p where :fechaActual < p.fechaLimite ")
    List<ProductoValido> listarProductosValidos (LocalDate fechaActual);

    @Query("select c.nombre, count(p) from Producto p join p.categoria c group by c")
    List<Object[]> obtenerTotalProductosPorCategoria();

    @Query("select p from Producto p where p.comentarioList is empty ")
    List<Producto> obtenerProductosSinComentarios();


    List<Producto> findByNombreContains(String nombre);

    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductosPorUsuario (p.vendedor.codigo, p.vendedor.email, count(p)) from Producto p group by p.vendedor")
    List<ProductosPorUsuario> obtenerProductoEnVenta();

    @Query("select avg(c.calificacion) from Producto p join p.comentarioList c where p.codigo = :codigo ")
    Float obtnerPromedioCalificaciones(Integer codigo);
}
