package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
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
public class AdministradorTeatro extends Persona implements Serializable {



    private String numeroTelefonico;


    @ToString.Exclude
    @OneToMany(mappedBy = "adminTeatro")
    private List<Teatro> teatros;

    @Builder
    public AdministradorTeatro(Integer cedula, String nombre, String correo, String contrasena, String numeroTelefonico) {
        super(cedula, nombre, correo, contrasena);
        this.numeroTelefonico = numeroTelefonico;
    }
}
