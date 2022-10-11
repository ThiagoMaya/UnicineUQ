package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Silla {

    @Id
    @EqualsAndHashCode.Include
    private Integer numero;

    @Column(nullable = false)
    private String fila;

    @Column(nullable = false)
    private String columna;


}
