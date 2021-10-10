package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
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
}
