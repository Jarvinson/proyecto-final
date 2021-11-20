package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ProductoServicio {
    Producto publicarProducto(Producto producto) throws Exception;

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    List<Producto> listarTodosProductos();

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    void comprarProducto(Compra compra) throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductosUsuario(Integer codigo) throws Exception;

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(Integer pos) throws Exception;
}
