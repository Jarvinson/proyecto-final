package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 10)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank(message = "Debe ingresar el nombre del producto")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "Debe ingresar el nombre de la publicación")
    @Column(nullable = false, length = 50)
    private String nombrePublicacion;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer unidades;

    @Lob
    @NotBlank(message = "Debe ingresar la descripción del producto")
    @Column(nullable = false, length = 100)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Future
    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @PositiveOrZero
    @Column(nullable = false)
    private Double descuento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @ManyToMany(mappedBy = "productosFavoritos")
    @ToString.Exclude
    private List<Usuario> usuarios;

    @ManyToMany
    private List<Categoria>categoria;

    @ManyToOne
    private Ciudad ciudad;

    @ElementCollection
    private List<String> imagenes;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentarioList;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> compra;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> subasta;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Chat> chats;
    // Este es el constructor de la clase Producto

    public Producto(String nombre, String nombrePublicacion, Integer unidades, String descripcion, Double precio,
                    Double descuento, LocalDateTime fechaLimite, Usuario vendedor) {
        this.nombre = nombre;
        this.nombrePublicacion = nombrePublicacion;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.fechaLimite = fechaLimite;
        this.vendedor = vendedor;
    }

    public String getImagenPrincipal(){
        if(imagenes != null && !imagenes.isEmpty()){
            return imagenes.get(0);
        }
        return "default.png";
    }

    public String getNameCategory(){
        for (Categoria name: categoria) {
                return name.getNombre();
        }
        return  null;
    }
}
