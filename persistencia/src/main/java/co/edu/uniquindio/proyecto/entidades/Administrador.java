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

<<<<<<< HEAD
=======
    public Administrador(Integer codigo, String nombre, String email, String password, Integer codigo1) {
        super(codigo, nombre, email, password);
        this.codigo = codigo1;
    }
>>>>>>> 574ee941f2a5b5791a882098282e7916525ce881
}
