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
public class Administrador extends Persona implements Serializable {



    @Column(nullable = false)
    private String numeroTelefonico;


    public Administrador(Integer cedula, String nombre, String correo, String contrasena, String numeroTelefonico) {
        super(cedula, nombre, correo, contrasena);
        this.numeroTelefonico = numeroTelefonico;
    }
}
