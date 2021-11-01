package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements Serializable {

    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank(message = "Debe ingresar una categoria")
    @Column(nullable = false, length = 100)
    private  String nombre;

    @ManyToMany(mappedBy = "categoria")
    @ToString.Exclude
    private List<Producto> producto;

    //Este es el metodo constructor de la clase Categoria
    public Categoria(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
