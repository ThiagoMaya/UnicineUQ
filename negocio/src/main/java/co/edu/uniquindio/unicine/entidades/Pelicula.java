package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
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

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 25)
    private List<Genero> generos;

    @Lob
    @Column(nullable = false)
    private String sinopsis;

    @Lob
    @Column(nullable = false)
    private String reparto;

    @Column(nullable = false)
    private String url_trailer;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @Column(nullable = false)
    private EstadoPelicula estado;

    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funcion;

    @Builder
    public Pelicula(String nombre, Genero genero, String sinopsis, String reparto, String url_trailer, EstadoPelicula estado) {
        this.nombre = nombre;
        this.generos = generos;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.url_trailer = url_trailer;
        this.estado = estado;
    }

    public String getImagenPrincipal(){
        if (!imagenes.isEmpty()) {
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }
        return "";
    }
}
