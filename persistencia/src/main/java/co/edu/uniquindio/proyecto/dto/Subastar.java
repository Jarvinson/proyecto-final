package co.edu.uniquindio.proyecto.dto;


import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Subastar {

    private LocalDate fechaLimite;
    private Producto producto;
    private Usuario usuario;
    private double valor;
    private LocalDate fechaSubasta;

}
