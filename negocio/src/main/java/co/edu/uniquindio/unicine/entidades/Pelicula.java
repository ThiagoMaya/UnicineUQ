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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pelicula implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 25)
    private Genero genero;

    @Column(nullable = false)
    private String sinopsis;

    @Column(nullable = false)
    private String reparto;

    @Column(nullable = false)
    private String url_trailer;

    @Column(nullable = false)
    private String url_imagen;

    @Column(nullable = false)
    private Boolean estado;

    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funcion;

    @Builder
    public Pelicula(String nombre, Genero genero, String sinopsis, String reparto, String url_trailer, String url_imagen, Boolean estado) {
        this.nombre = nombre;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.url_trailer = url_trailer;
        this.url_imagen = url_imagen;
        this.estado = estado;
    }
}
