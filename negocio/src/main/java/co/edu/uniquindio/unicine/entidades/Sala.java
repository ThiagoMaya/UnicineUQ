package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    private DistribucionSillas distribucionSillas;

    @ToString.Exclude
    @OneToMany(mappedBy = "sala")
    private List<Funcion> funcion;

    @Builder
    public Sala(String nombre, Teatro teatro, DistribucionSillas distribucionSillas) {
        this.nombre = nombre;
        this.teatro = teatro;
        this.distribucionSillas = distribucionSillas;
    }
}
