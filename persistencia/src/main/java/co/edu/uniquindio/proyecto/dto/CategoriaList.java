package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CategoriaList {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre;
    private String imagen;

}
