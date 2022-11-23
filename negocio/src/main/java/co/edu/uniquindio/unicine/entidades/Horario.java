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
    private String hora;

    @Column(nullable = false)
    private String dia ;

    @Column(nullable = false)
    private Date fecha;

    @ToString.Exclude
    @OneToMany(mappedBy = "horario")
    private List<Funcion> funcion;

   @Builder
    public Horario(Integer codigo, String hora, String dia, Date fecha) {
        Codigo = codigo;
        this.hora = hora;
        this.dia = dia;
        this.fecha = fecha;
    }
}
