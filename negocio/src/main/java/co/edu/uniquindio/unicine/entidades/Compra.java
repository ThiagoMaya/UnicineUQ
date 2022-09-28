package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCompra;

    private Date fechaCompra;

    private String metodo_pago;

    private float subtotal;

    private float total;

    @ManyToMany
    private List<Confiteria>confiteria;

    @ManyToOne
    private Cliente cliente;


}
