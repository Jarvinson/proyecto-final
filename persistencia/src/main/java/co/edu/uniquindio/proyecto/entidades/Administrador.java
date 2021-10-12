package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Administrador extends Persona implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    public Administrador(Integer codigo, String nombre, String email, String password, Integer codigo1) {
        super(codigo, nombre, email, password);
        this.codigo = codigo1;
    }

}
