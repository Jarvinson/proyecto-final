package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Getter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar(){
        this.productos = productoServicio.listarTodosProductos();
    }

    public String irAlDetalle(Integer id) {
        return "/detalle_producto?faces-redirect=true&amp;producto="+id;
    }
}
