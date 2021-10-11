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
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

<<<<<<< HEAD
    @Column(nullable = false, length = 10)
=======
    @Column(nullable = false, unique = true)
>>>>>>> 574ee941f2a5b5791a882098282e7916525ce881
    private String username;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentarioList;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    private List<Producto>productoList;

    @ManyToMany(mappedBy = "usuario")
    private List<Producto>producto;

    @ManyToOne
    @JoinColumn(name = "codigoCiudad", nullable = false)
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

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> numTelefonos;

<<<<<<< HEAD
    public Usuario(Integer codigo, String nombre, String email, String password, String username, Ciudad ciudad) {
        super(codigo, nombre, email, password);
        this.username = username;
        this.ciudad = ciudad;
=======
    public Usuario(Integer codigo, String nombre, String email, String password, String username) {
        super(codigo, nombre, email, password);
        this.username = username;
>>>>>>> 574ee941f2a5b5791a882098282e7916525ce881
    }
}
