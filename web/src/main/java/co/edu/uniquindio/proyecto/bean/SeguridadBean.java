package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private SubastaServicio subastaServicio;

    @Getter @Setter
    private SubastaUsuario subastaUsuario;

    @Getter @Setter
    private List<String> medioPago;

    @Getter @Setter
    private List<SubastaUsuario> subastasUsuarios;

    @Getter @Setter
    private List<Producto> productosSubasta;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private List<DetalleCompra> comprasUsuario;

    @Getter @Setter
    private List<Producto> productosFavoritos;

    @Getter @Setter
    private List<Producto> productosUsuarios;

    @Getter @Setter
    private List<Producto> selectedProducts;

    @Getter @Setter
    private LocalDate fechaLimite, fechaSubasta;

    @Getter @Setter
    private double valorSubasta;

    @Getter @Setter
    private Double subTotal;

    @Getter @Setter
    private Compra compra;

    @Getter @Setter
    private String medioP;


    @PostConstruct
    void inicializar(){
        this.medioP = "";
        this.subTotal = 0.0;
        this.valorSubasta = 0.0;
        this.fechaLimite = null;
        this.fechaSubasta = null;
        this.productosCarrito = new ArrayList<>();
        this.comprasUsuario = new ArrayList<>();
        this.productosFavoritos = new ArrayList<>();
        this.productosUsuarios = new ArrayList<>();
        this.subastaUsuario = new SubastaUsuario();
        this.subastasUsuarios = new ArrayList<>();
        this.productosSubasta = new ArrayList<>();
        this.medioPago = new ArrayList<>();

    }

    public String iniciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                usuarioSesion = usuarioServicio.iniciarSesion(email, password);
                autenticado = true;
                comprasUsuario();
                listarProductosFavoritos();
                listarProductosUsuario();
                listarProductosSubasta();
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


        medioPago.add("Tarjeta débito");
        medioPago.add("Tarjeta crédito");
        medioPago.add("PSE");

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
                productoServicio.realizarCompra(usuarioSesion, productosCarrito, "PSE" );
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Compra realizada");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
                triggerMail();
                subTotal =0.0;
                productosCarrito.clear();
                productosCompra();

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

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " productos seleccionados" : "1 producto seleccionado";
        }
        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.productosUsuarios.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Productos Eliminados"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void deleteProduct() {
        this.productosUsuarios.remove(this.producto);
        this.producto = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void updateProduct(){
        try {
            productoServicio.actualizarProducto(this.producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Actualizado"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void updateSubasta(){
        try {
            productoServicio.actualizarProducto(this.producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Oferta subasta Actualizada"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public Integer getCalificacionPromedio(int codigo){
        try {
            return comentarioServicio.calificacionPromedio(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getNameCategory(int codigo){
        try {
            return productoServicio.obtenerCategoria(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void triggerMail(){

            String mensaje = "<h1>UNISHOP</h1>";

            mensaje += "<h2>Hola, " + usuarioSesion.getNombre() + "</h2>"
                    + "\n\nTu pedido ha sido confirmado, llegará en los próximos días.\n"
                    + "\n<h4>DETALLES DE LA COMPRA</h4>"
                    + "<P>" + productosCompra() + "</P>"
                    + "</br>SubTotal: $" + subTotal
                    + "<h2>Total compra: $" + subTotal
                    + "</h2></br></br>Atentamente, "
                    + "<h3>UNISHOP</h3>";
            try {
                emailSenderService.sendSimpleEmail("jarvinsonv30@gmail.com", mensaje,
                               "Compra Unishop");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public String productosCompra(){
        String nombre = "";
        for (ProductoCarrito p: productosCarrito) {
             nombre +=  p.getNombre().toUpperCase()
                     + "<br>Cantidad: " + p.getUnidades()
                     + "<br>$"+ p.getPrecio() + "<br><br>";
        }
        return nombre;
    }

    public void crearSubasta(int codigo){

        if(usuarioSesion != null ){
            try {
                Producto producto1 = productoServicio.obtenerProducto(codigo);
                subastaServicio.crearSubasta(fechaLimite, producto1, usuarioSesion, valorSubasta, fechaSubasta);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Subasta creada");
                FacesContext.getCurrentInstance().addMessage("subasta-msj", fm);
                productosSubasta.clear();
                listarProductosSubasta();

            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("subasta-msj", fm);
            }
        }
    }

    private void listarProductosSubasta(){
        for (Subasta s: subastaServicio.listarSubastas()) {
            productosSubasta.add(s.getProducto());

        }
    }


}


