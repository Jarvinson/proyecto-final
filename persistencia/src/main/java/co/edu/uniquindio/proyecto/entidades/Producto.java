package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
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

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

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

    public Producto(Integer codigo, String nombre, Integer unidades, String descripcion, Double precio,
                    LocalDate fechaLimite, Double descuento, Usuario vendedor, List<Usuario> usuario,
                    List<Categoria> categoria, Ciudad ciudad, Map<String, String> imagenes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.descuento = descuento;
        this.vendedor = vendedor;
        this.usuario = usuario;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.imagenes = imagenes;
    }
}
