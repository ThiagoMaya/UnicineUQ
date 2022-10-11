package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AdministradorTeatro implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String cedula;

    private String nombre;

    private String email;

    private String numeroTelefonico;

    private String contraseña;

    @OneToMany(mappedBy = "teatro")
    private List<Teatro> teatros;

    @Builder

    public AdministradorTeatro(String cedula, String nombre, String email, String numeroTelefonico, String contraseña) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.numeroTelefonico = numeroTelefonico;
        this.contraseña = contraseña;
    }
}
