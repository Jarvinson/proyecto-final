package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @PostConstruct
    public void inicializar(){

        usuario = new Usuario();
        ciudades = ciudadServicio.listarCiudades();
    }

    public void registrarUsuario(){
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
    }
}
