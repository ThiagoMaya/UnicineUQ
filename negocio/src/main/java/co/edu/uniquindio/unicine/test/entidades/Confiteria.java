package co.edu.uniquindio.unicine.test.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Confiteria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idProducto;

    private String nombre;

    private String descripcion;

    private String estado;

    private float precio;

    @ManyToMany(mappedBy = "confiteria")
    private List<Compra> compra;
}
