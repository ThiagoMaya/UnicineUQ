package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@ToString
public class Cliente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String cedula;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false, unique = true)
    @Email
    private String email;

    @ElementCollection
    private Map<String,String> telefonos;

    private String contraseña;

}
