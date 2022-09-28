package co.edu.uniquindio.unicine.test.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
