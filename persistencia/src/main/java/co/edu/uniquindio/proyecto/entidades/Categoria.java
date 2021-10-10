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
public class Categoria implements Serializable {

    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 30)
    private  String nombre;

    @ManyToMany(mappedBy = "categoria")
    private List<Producto> producto;

    public Categoria(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
