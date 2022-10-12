package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DistribucionSillas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String esquema;

    @Column(nullable = false)
    private Integer total_Sillas;

    @Column(nullable = false)
    private Integer filas;

    @Column(nullable = false)
    private Integer columnas;

    @OneToMany(mappedBy = "distribucionSillas")
    private List <Sala> listaSalas;

    @Builder
    public DistribucionSillas(String esquema, Integer total_Sillas, Integer filas, Integer columnas) {
        this.esquema = esquema;
        this.total_Sillas = total_Sillas;
        this.filas = filas;
        this.columnas = columnas;
    }
}

