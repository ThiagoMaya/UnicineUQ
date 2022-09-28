package co.edu.uniquindio.unicine.test.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

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
    private List<String> telefonos;

    private String contraseña;

    //@OneToMany(mappedBy = "cliente")
    //private List<Compra> compras;

}
