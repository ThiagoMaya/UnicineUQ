package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer Codigo;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Date fecha_Inicio;

    @Column(nullable = false)
    private Date fecha_fin;

    @ToString.Exclude
    @OneToMany(mappedBy = "horario")
    private List<Funcion> funcion;

    @Builder
    public Horario(Date fecha, Date fecha_Inicio, Date fecha_fin) {
        this.fecha = fecha;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_fin = fecha_fin;
    }
}
