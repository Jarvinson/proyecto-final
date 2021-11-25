package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;

import java.util.List;

public interface DetalleCompraServicio {

    DetalleCompra registrarDetalleCompra(DetalleCompra dc) throws Exception;

    DetalleCompra actualizarDetalleCompra(DetalleCompra dc) throws Exception;

    void eliminarDetalleCompra(Integer id) throws Exception;

    DetalleCompra obtenerDetalleCompra(Integer id) throws Exception;

    List<DetalleCompra> listarDetalleCompra();


}
