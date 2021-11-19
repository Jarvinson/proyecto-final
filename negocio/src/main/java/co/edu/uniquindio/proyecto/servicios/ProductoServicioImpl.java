package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
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
    public void actualizarProducto(Producto p) throws Exception {

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
        return null;
    }

    @Override
    public void comentarProducto(String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws Exception {

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
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {
        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductosUsuario(Integer codigo) throws Exception {
        return null;
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Integer pos) throws Exception {
        return categoriaRepo.findById(pos).orElseThrow(() -> new Exception("El id no corresponde a ninguna categoria"));
    }
}
