package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor

// La clase persona hereda de la clase Usuario
public class Persona implements Serializable {

    @Id
    @Column(nullable = false, length = 10)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank(message = "Debe ingresar el nombre de usuario")
    @Column(length = 100, nullable = false)
    private String nombre;

    @Email
    @NotBlank(message = "Debe ingresar un email")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Debe ingresar una contraseña")
    @Column(nullable = false, length = 30)
    //@JsonIgnore
    private String password;


}
