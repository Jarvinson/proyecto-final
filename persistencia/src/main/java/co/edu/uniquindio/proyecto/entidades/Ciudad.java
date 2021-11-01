package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank(message = "Debe ingresar una ciudad")
    @Column(length = 100, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Usuario> usuario;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Producto> producto;

    //Este es el metodo constructor de la clase ciudad
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
