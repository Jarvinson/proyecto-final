package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['producto']}")
    private Integer codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    @PostConstruct
    public void inicializar() {

        nuevoComentario = new Comentario();
        if(codigoProducto!=null) {
            try {
                producto = productoServicio.obtenerProducto(codigoProducto);
                this.comentarios = producto.getComentarioList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void crearComentario(){

        try {
            nuevoComentario.setProducto(producto);
            nuevoComentario.setUsuario(usuarioServicio.obtenerUsuario(123));
            productoServicio.comentarProducto(nuevoComentario);
            this.comentarios.add(nuevoComentario);
            nuevoComentario = new Comentario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
