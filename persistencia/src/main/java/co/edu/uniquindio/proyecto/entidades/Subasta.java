package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subasta implements Serializable {

    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;

    @ManyToOne
    @JoinColumn(name = "codigoProducto", nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuario;

    // Este es el constructor de la clase Subasta
    public Subasta(LocalDate fechaLimite, Producto producto) {
        this.fechaLimite = fechaLimite;
        this.producto = producto;
    }
}
