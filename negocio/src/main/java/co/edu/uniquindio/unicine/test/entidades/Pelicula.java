package co.edu.uniquindio.unicine.test.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pelicula implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String nombre;

    private String genero;

    private String sinopsis;

    private String reparto;

    private String estado;


}
