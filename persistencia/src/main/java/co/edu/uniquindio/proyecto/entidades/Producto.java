package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Producto implements Serializable {

    @Id
    @Column(nullable = false, length = 10)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Column(nullable = false)
    private Double descuento;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentarioList;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @ManyToMany
    private List<Usuario> usuario;

    @ManyToMany
    private List<Categoria>categoria;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    private List<DetalleCompra> compra;

    @OneToMany(mappedBy = "producto")
    private List<Subasta> subasta;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @OneToMany(mappedBy = "producto")
    private List<Chat> chats;

    private Integer codigoVendedor;

}
