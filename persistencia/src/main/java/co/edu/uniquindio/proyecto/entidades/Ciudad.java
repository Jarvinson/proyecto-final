package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
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

    @Column(length = 100, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Usuario> usuario;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Producto> producto;

<<<<<<< HEAD
    public Ciudad(String nombre) {
=======

    public Ciudad(Integer codigo, String nombre) {
        this.codigo = codigo;
>>>>>>> 574ee941f2a5b5791a882098282e7916525ce881
        this.nombre = nombre;
    }
}
