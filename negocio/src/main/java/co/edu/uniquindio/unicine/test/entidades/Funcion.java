package co.edu.uniquindio.unicine.test.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Funcion implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idFuncion;

    private Date fecha;

    private String hora;

    private String tipo;

    private String estado;

    private float precio;


}
