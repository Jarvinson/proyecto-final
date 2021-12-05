package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.CategoriaList;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Getter
    private List<Producto> productos;

    @Getter
    private List<CategoriaList> categoriaList;

    @Getter @Setter
    private CategoriaList categoria;

    @PostConstruct
    public void inicializar(){

        this.productos = productoServicio.listarTodosProductos();
        categoria = new CategoriaList();
        categoriaList = new ArrayList<>();
        listarCategorias();

    }

    public String irAlDetalle(Integer id) {
        return "/detalle_producto?faces-redirect=true&amp;producto="+id;
    }

    public void listarCategorias(){

        for (Categoria c: categoriaServicio.listarCategorias()) {

            if (c.getNombre().equals("Tecnologia")){
                categoria.setId(c.getCodigo());
                categoria.setNombre(c.getNombre());
                categoria.setImagen("tecnologia.jpeg");
                categoriaList.add(categoria);

            }
            if (c.getNombre().equals("Moda")){
                categoria.setId(c.getCodigo());
                categoria.setNombre(c.getNombre());
                categoria.setImagen("moda.jpg");
                categoriaList.add(categoria);

            }
            if (c.getNombre().equals("Deporte")){
                categoria.setId(c.getCodigo());
                categoria.setNombre(c.getNombre());
                categoria.setImagen("deporte.jpg");
                categoriaList.add(categoria);

            }
            if (c.getNombre().equals("Hogar")){
                categoria.setId(c.getCodigo());
                categoria.setNombre(c.getNombre());
                categoria.setImagen("hogar.jpg");
                categoriaList.add(categoria);

            }
            if (c.getNombre().equals("Herramientas")){
                categoria.setId(c.getCodigo());
                categoria.setNombre(c.getNombre());
                categoria.setImagen("herramientas.jpg");
                categoriaList.add(categoria);

            }
            if (c.getNombre().equals("Vehiculos")){
                categoria.setId(c.getCodigo());
                categoria.setNombre(c.getNombre());
                categoria.setImagen("vehi.jpg");
                categoriaList.add(categoria);

            }
            if (c.getNombre().equals("Construccion")){
                categoria.setId(c.getCodigo());
                categoria.setNombre(c.getNombre());
                categoria.setImagen("constru.jpg");
                categoriaList.add(categoria);

            }
            categoria = new CategoriaList();

        }

    }

}
