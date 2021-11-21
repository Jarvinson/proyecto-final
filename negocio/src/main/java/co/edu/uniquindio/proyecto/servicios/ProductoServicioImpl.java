package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;
    private final CategoriaRepo categoriaRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo, CategoriaRepo categoriaRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
        this.categoriaRepo = categoriaRepo;
    }

    //Función que permite la publicación de un producto
    @Override
    public Producto publicarProducto(Producto producto) throws Exception {
       try {
           return productoRepo.save(producto);
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
    }

    @Override
    public Producto actualizarProducto(Producto p) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(p.getCodigo());

        if (buscado.isEmpty()) {
            throw new Exception("El producto no existe");
        }

        return productoRepo.save(p);

    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);

        if(producto.isEmpty()){
            throw new Exception("El códgigo del producto no existe");
        }

        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(() -> new Exception("El código del producto no es válido"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {

        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public List<Producto> listarTodosProductos() {

        return productoRepo.findAll();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDateTime.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public void guardarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProducto(Compra compra) throws Exception {

    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros){
        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductosUsuario(Integer codigo) throws Exception {
        return productoRepo.listarProductosUsuario(codigo);
    }


}
