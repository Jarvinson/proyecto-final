package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;

import java.util.List;

public interface CompraServicio {

    Compra registrarCompra(Compra c) throws Exception;

    Compra actualizarCompra(Compra c) throws Exception;

    void eliminarCompra(Integer id) throws Exception;

    Compra obtenerCompra(Integer id) throws Exception;

    List<Compra> listaCompras();

    List<Compra> findAllByMedioPago(String nombre);

    Long obtenerTotalProductosCompradosUsuario(Integer codigo);

    Long calcularTotalVentas(Integer codigo);

    Long calcularTotalCompras(Integer codigo);




}
