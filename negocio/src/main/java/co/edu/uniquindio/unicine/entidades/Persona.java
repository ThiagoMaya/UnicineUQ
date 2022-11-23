package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Persona implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private Integer cedula;

    @Column(length = 100, nullable = false)
    private String nombre;

    @NonNull
    @Column(length = 100, nullable = false, unique = true)
    @Email
    private String email;

    @ToString.Exclude
    @Column(length = 16, nullable = false)
    private String contrasena;

    public Persona(Integer cedula, String nombre, String email, String contrasena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }
}
