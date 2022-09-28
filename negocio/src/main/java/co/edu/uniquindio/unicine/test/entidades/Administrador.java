package co.edu.uniquindio.unicine.test.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Administrador implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String cedula;

    private String nombre;

    private String email;

    private String numeroTelefonico;

    private String contraseña;

}
