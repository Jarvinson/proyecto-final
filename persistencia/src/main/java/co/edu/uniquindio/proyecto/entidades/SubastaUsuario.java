package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class SubastaUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate fechaSubasta;

    @ManyToOne
    @JoinColumn(name = "codigoUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "codigoSubasta", nullable = false)
    private Subasta subasta;

    // Este es el constructor de la clase SubastaUsuario
    public SubastaUsuario(Integer codigo, Double valor, LocalDate fechaSubasta, Usuario usuario, Subasta subasta) {
        this.codigo = codigo;
        this.valor = valor;
        this.fechaSubasta = fechaSubasta;
        this.usuario = usuario;
        this.subasta = subasta;
    }

}
