package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCompra;

    @NotBlank(message = "Debe ingresar un medio de pago")
    @Column(nullable = false, length = 30)
    private String medioPago;

    @ManyToOne
    @JoinColumn(name = "codigoUsuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompra;

    // Este es el constructor de la clase Compra
    public Compra(String medioPago, Usuario usuario) {
        this.medioPago = medioPago;
        this.usuario = usuario;
    }
}
