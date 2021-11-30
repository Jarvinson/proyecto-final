package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio  {

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;
    private final CategoriaRepo categoriaRepo;
    private final CompraRepo compraRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final UsuarioRepo usuarioRepo;




    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo, CategoriaRepo categoriaRepo,
                                CompraRepo compraRepo, DetalleCompraRepo detalleCompraRepo, UsuarioRepo usuarioRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
        this.categoriaRepo = categoriaRepo;
        this.compraRepo = compraRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.usuarioRepo = usuarioRepo;
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
        Usuario usuario1 = usuarioRepo.findById(usuario.getCodigo()).get();

           if(!usuario1.getProductosFavoritos().contains(producto)) {
               usuario1.getProductosFavoritos().add(producto);
               usuarioRepo.save(usuario1);
           }else{
               throw new Exception("El producto ya está agregado en la lista de favoritos");
           }
    }

    @Override
    public List<Producto> listarProductosFavoritos(Integer codigo) {
        return productoRepo.listarProductosFavoritos(codigo);
    }

    @Override
    public void eliminarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception {
       try{
           Usuario usuario1 = usuarioRepo.findById(usuario.getCodigo()).get();
           List<Producto> productosF;
           productosF = usuario1.getProductosFavoritos();
           productosF.remove(producto);
           productoRepo.eliminarFavoritos(producto.getCodigo(), usuario.getCodigo());

           /**
           usuario1.getProductosFavoritos().clear();
           for (Producto p: productosF) {
               usuario1.getProductosFavoritos().add(p);
           }
            */
           System.out.println(usuario1.getProductosFavoritos().size()+"!!!!!!!!!!!!!!!!");
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros){
        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductosUsuario(Integer codigo) throws Exception {
        return productoRepo.listarProductosUsuario(codigo);
    }

    @Override
    public Compra realizarCompra(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception {
       try {
           Compra compra = new Compra();
           compra.setFechaCompra(LocalDateTime.now(ZoneId.of("America/Bogota")));
           compra.setUsuario(usuario);
           compra.setMedioPago(medioPago);

           Compra compraGuardada = compraRepo.save(compra);

           DetalleCompra dc;
           for(ProductoCarrito p:productos){
               dc = new DetalleCompra();
               dc.setCompra(compraGuardada);
               dc.setPrecioProducto(p.getPrecio());
               Producto producto = productoRepo.findById(p.getId()).get();

                 if(producto.getUnidades() >= p.getUnidades()){
                     dc.setUnidades(p.getUnidades());
                     dc.setProducto(productoRepo.findById(p.getId()).get());
                     producto.setUnidades(producto.getUnidades() - p.getUnidades());
                 }else{
                     throw new Exception("No hay suficientes unidades disponibles");
                 }

                //Verificar unidades disponibles - medio pago
               //Email compra
               detalleCompraRepo.save(dc);
           }
           return compraGuardada;
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }


    }



}
