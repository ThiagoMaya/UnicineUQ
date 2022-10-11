package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String nombre;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Teatro teatro;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DistribucionSIllas distribucionSillas;

    @OneToMany(mappedBy = "sala")
    private List<Funcion> funcion;

    @Builder
    public Sala(String nombre, Teatro teatro, DistribucionSIllas distribucionSillas) {
        this.nombre = nombre;
        this.teatro = teatro;
        this.distribucionSillas = distribucionSillas;
    }
}
