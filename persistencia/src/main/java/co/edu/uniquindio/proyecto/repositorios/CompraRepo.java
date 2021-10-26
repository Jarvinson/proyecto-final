package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Repositorio de la clase Compra
public interface CompraRepo extends JpaRepository<Compra, Integer> {
    List<Compra> findAllByMedioPago(String nombre);
    Page<Compra> findAll(Pageable paginador);

    @Query("select count(distinct d.producto) from Compra c join c.detalleCompra d where c.usuario.codigo = :codigo")
    Long obtenerListaProductosComprados(Integer codigo);

    @Query("select sum(d.precioProducto*d.unidades) from DetalleCompra d where d.producto.vendedor.codigo = :codigo ")
    Long calcularTotalVentas(Integer codigo);

    @Query("select sum(d.precioProducto*d.unidades) from Compra c join c.detalleCompra d where c.usuario.codigo = :codigo ")
    Long calcularTotalCompras(Integer codigo);

    @Query("select c, count(p) as total from Producto p join p.categoria c group by c order by total desc")
    List<Object[]> obtenerCategoriaMasUsada ();


}
