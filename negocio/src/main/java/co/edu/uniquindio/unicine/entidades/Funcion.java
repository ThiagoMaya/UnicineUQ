package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Funcion implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer Codigo;

    @Positive
    @Column(nullable = false)
    private Integer precio;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Horario horario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Sala sala;

    @OneToMany(mappedBy = "funcion")
    private List<Compra> compra;

    @Builder
    public Funcion(Integer precio, Pelicula pelicula, Horario horario, Sala sala) {
        this.precio = precio;
        this.pelicula = pelicula;
        this.horario = horario;
        this.sala = sala;
    }
}
