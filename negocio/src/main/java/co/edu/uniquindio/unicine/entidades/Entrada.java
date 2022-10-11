package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Entrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer Codigo;

    @Positive
    @Column(nullable = false)
    private Integer precio;


    @Column(nullable = false)
    private String fila;

    @Column(nullable = false)
    private String columna;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra compra;

    @Builder
    public Entrada(Integer precio, String fila, String columna, Compra compra) {
        this.precio = precio;
        this.fila = fila;
        this.columna = columna;
        this.compra = compra;
    }
}
