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
public class Confiteria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idProducto;

    @Column( nullable = false)
    private String nombre;

    @Column( nullable = false)
    private String descripcion;

    @Column( nullable = false)
    private String estado;

    @Column( nullable = false)
    private String imagen;

    @Column( nullable = false)
    private float precio;

    @ToString.Exclude
    @OneToMany(mappedBy = "confiteria")
    private List<CompraConfiteria> compraConfiterias;

    @Builder
    public Confiteria(String nombre, String descripcion, String estado, float precio, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.precio = precio;
        this.imagen = imagen;
    }
}
