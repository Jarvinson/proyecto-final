package co.edu.uniquindio.proyecto.converter;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class ProductoConverter implements Converter<Producto>, Serializable {
    @Autowired
    private ProductoServicio productoServicio;


    @Override
    public Producto getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Producto producto = null;

        try {
            producto = productoServicio.obtenerProducto(Integer.parseInt(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Producto producto) {
        if(producto != null){
            return producto.getCodigo().toString();
        }
        return "";
    }
}
