package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank(message = "Debe ingresar un mensaje")
    @Column(nullable = false)
    private String Mensaje;

    @NotBlank(message = "Debe ingresar un mensaje")
    @Column(nullable = false, length = 50)
    private  String emisor;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "codigoChat", nullable = false)
    private Chat chat;
    // Este es el constructor de la clase Mensaje
    public Mensaje(String mensaje, String emisor, Chat chat) {
        this.Mensaje = mensaje;
        this.emisor = emisor;
        this.chat = chat;
    }
}
