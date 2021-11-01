package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentarioList;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    private List<Producto>productoList;

    @ManyToMany
    @ToString.Exclude
    private List<Producto>productosFavoritos;

    @ManyToOne
    @JoinColumn(name = "codigoCiudad")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> compra;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuario;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Chat> chats;

    @ElementCollection //Etiqueta que permite la normalizacion del atributo telefono
    private Map<String, String> numTelefonos;

    // Este es el constructor de la clase Usuario
    public Usuario(Integer codigo, String nombre, String email, String password, String username, Ciudad ciudad, Map<String, String> numTelefonos) {
        super(codigo, nombre, email, password);
        this.username = username;
        this.ciudad = ciudad;
        this.numTelefonos = numTelefonos;
    }
}
