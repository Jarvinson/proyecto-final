package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    private Double precioProducto;

    @ManyToOne
    @JoinColumn(name = "codigoCompra",nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "codigoProducto",nullable = false)
    private Producto producto;
    // Este es el constructor de la clase DetalleCompra
    public DetalleCompra(Integer codigo, Integer unidades, Double precioProducto, Compra compra, Producto producto) {
        this.codigo = codigo;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.compra = compra;
        this.producto = producto;
    }
}
