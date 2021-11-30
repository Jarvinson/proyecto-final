package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Scope("session")
@Component
public class SeguridadBean implements Serializable{

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Getter @Setter
    private Producto producto;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private List<DetalleCompra> comprasUsuario;

    @Getter @Setter
    private List<Producto> productosFavoritos;

    @Getter @Setter
    private List<Producto> productosUsuarios;

    @Getter @Setter
    private Double subTotal;

    @Getter @Setter
    private String medioPago;

    @Getter @Setter
    private Compra compra;

    @PostConstruct
    void inicializar(){
        this.subTotal = 0.0;
        this.productosCarrito = new ArrayList<>();
        this.comprasUsuario = new ArrayList<>();
        this.productosFavoritos = new ArrayList<>();
        this.productosUsuarios = new ArrayList<>();
    }

    public String iniciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                usuarioSesion = usuarioServicio.iniciarSesion(email, password);
                autenticado = true;
                comprasUsuario();
                listarProductosFavoritos();
                listarProductosUsuario();
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }

        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarCarrito(Integer id, Double precio, String nombre, String imagen){

        ProductoCarrito pc = new ProductoCarrito(id, nombre, imagen, precio, 1);

        if (!productosCarrito.contains(pc)) {
            productosCarrito.add(pc);
            subTotal += precio;

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        } else {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", "Producto ya agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }
    }

    public  void eliminarCarrito(int index){
        subTotal -= productosCarrito.get(index).getPrecio();
        productosCarrito.remove(index);
    }

    public void actualizarSubTotal(){
        subTotal = 0.0;
        for (ProductoCarrito pc: productosCarrito) {
            subTotal += pc.getPrecio()*pc.getUnidades();
        }
    }

    public void comprar(){
        if(usuarioSesion != null && !productosCarrito.isEmpty()){
            try {

                productoServicio.realizarCompra(usuarioSesion, productosCarrito, "medioPago" );
                productosCarrito.clear();
                subTotal =0.0;

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Compra realizada");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }
    }

    public void productosFavoritos(Producto producto){
        if(producto != null) {
            try {
                productoServicio.guardarProductoEnFavoritos(producto, usuarioSesion);
                listarProductosFavoritos();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Producto agregado a favoritos");
                FacesContext.getCurrentInstance().addMessage("favorites", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("favorites", fm);
            }
        }
    }

    public void listarProductosFavoritos() {
        try {
            for (Producto p: productoServicio.listarProductosFavoritos(usuarioSesion.getCodigo())) {
                if (!productosFavoritos.contains(p)) {
                    productosFavoritos.add(p);
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public void listarProductosUsuario(){
        try{
            for (Producto p: productoServicio.listarProductosUsuario(usuarioSesion.getCodigo())) {
                if(!productosUsuarios.contains(p)){
                    productosUsuarios.add(p);
                    System.out.println(p.getImagenPrincipal());
                }
            }
        }catch (Exception e){

        }
    }

    public void elimanarFavorito(int codigo){

        try {
            Producto p = productoServicio.obtenerProducto(codigo);
            productoServicio.eliminarProductoEnFavoritos(p, usuarioSesion);
            productosFavoritos.remove(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void comprasUsuario(){

        try {
            for (DetalleCompra c: compraServicio.listarComprasUsuario(usuarioSesion.getCodigo())) {
                if(!comprasUsuario.contains(c)){
                    comprasUsuario.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


