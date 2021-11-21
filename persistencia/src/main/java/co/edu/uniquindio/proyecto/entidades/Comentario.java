package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
public class Comentario implements Serializable {

    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank(message = "Debe ingresar un mensaje")
    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = true)
    private String respuesta;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaComentario;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer calificacion;

    @ManyToOne
    @JoinColumn(name = "codigoProducto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "codigoUsuario", nullable = false)
    private Usuario usuario;

    // Este es el constructor de la clase Comentario
    public Comentario(String mensaje, String respuesta,
                      Integer calificacion, Producto producto, Usuario usuario) {
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.calificacion = calificacion;
        this.producto = producto;
        this.usuario = usuario;
    }


    public String getFechaEstilo(){
        return fechaComentario.format(DateTimeFormatter.ISO_DATE);
    }
}