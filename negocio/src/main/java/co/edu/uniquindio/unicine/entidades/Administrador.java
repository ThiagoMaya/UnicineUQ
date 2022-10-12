package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Administrador implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer cedula;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String numeroTelefonico;

    @Column(nullable = false)
    private String contraseña;

    @Builder
    public Administrador(Integer cedula, String nombre, String email, String numeroTelefonico, String contraseña) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.numeroTelefonico = numeroTelefonico;
        this.contraseña = contraseña;
    }
}
