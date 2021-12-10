package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
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
    Long obtenerTotalProductosComprados(Integer codigo);

    @Query("select d from Compra c join c.detalleCompra d where c.usuario.codigo = :codigo")
    List<DetalleCompra> listarComprasUsuario(Integer codigo);

    @Query("select sum(d.precioProducto*d.unidades) from DetalleCompra d where d.producto.vendedor.codigo = :codigo ")
    Long calcularTotalVentas(Integer codigo);

    @Query("select sum(d.precioProducto*d.unidades) from Compra c join c.detalleCompra d where c.usuario.codigo = :codigo ")
    Long calcularTotalCompras(Integer codigo);

    @Query("select month(), count(c.codigo) from Compra c join c.detalleCompra d where c.usuario.codigo = :codigo ")
    Long calcularTotalComprasMes(Integer codigo);



}
