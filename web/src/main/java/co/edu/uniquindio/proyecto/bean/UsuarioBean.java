package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;

import co.edu.uniquindio.proyecto.entidades.Usuario;

import co.edu.uniquindio.proyecto.servicios.EmailSenderService;
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
    private String userName;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostConstruct
    public void inicializar() {

        usuario = new Usuario();
        userName = " ";
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

    public void recuperarContraseña( ){

        try {
            usuario = usuarioServicio.obtenerUsuarioUsername(userName.trim());

            String mensaje = "<h1>UNISHOP</h1>";

            mensaje += "<h2>Hola, " + usuario.getNombre() + "</h2>"
                    + "<h3></br>Tu contraseña es: " + usuario.getPassword() + "</h3>"
                    + "</h2></br></br>Atentamente, "
                    + "<h3>UNISHOP</h3>";

            emailSenderService.sendSimpleEmail("jarvinsonv30@gmail.com", mensaje,
                    "Recuperar contraseña");
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Contraseña enviada al correo electrónico");
            FacesContext.getCurrentInstance().addMessage("password", facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("password", facesMsg);
        }
    }


    public String irPassword() {
        return "/recuperar_contraseña?faces-redirect=true&amp";
    }
}
