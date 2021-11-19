package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;

    @PostConstruct
    public void inicializar(){

        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        categorias = productoServicio.listarCategorias();
        ciudades = ciudadServicio.listarCiudades();
    }

    public void crearProducto(){
        try {
            if(!imagenes.isEmpty()) {
                Usuario usuario = usuarioServicio.obtenerUsuario(123);
                producto.setVendedor(usuario);
                producto.setImagenes(imagenes);
                producto.setFechaLimite(LocalDateTime.now().plusMonths(1));
                productoServicio.publicarProducto(producto);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Producto creado");
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alert", "Debe subir al menos una imagen");
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if ( nombreImagen != null){
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile imagen){

        try {
            File archivo = new File(urlUploads + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            try {
                IOUtils.copy(imagen.getInputStream(), outputStream);
                return imagen.getFileName();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       return null;

    }

}
