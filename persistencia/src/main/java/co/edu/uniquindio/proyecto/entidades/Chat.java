package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @OneToMany(mappedBy = "chat")
    @ToString.Exclude
    private List<Mensaje> mensajes;

    @ManyToOne
    @JoinColumn(name = "codigoUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "codigoProducto", nullable = false)
    private Producto producto;

    // Este es el contructor de la clase chat
    public Chat(Usuario usuario, Producto producto) {
        this.usuario = usuario;
        this.producto = producto;
    }
}
