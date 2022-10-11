package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Teatro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String nombre;

    private String direccion;

    private String telefono;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AdministradorTeatro adminTeatro;



    @Builder

    public Teatro(String nombre, String direccion, String telefono, Ciudad ciudad, AdministradorTeatro adminTeatro) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.adminTeatro = adminTeatro;
    }
}
