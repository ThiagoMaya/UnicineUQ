package co.edu.uniquindio.unicine.entidades;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CuponCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private boolean estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cupon cupon;

    @ToString.Exclude
    @OneToOne(mappedBy = "cuponCliente")
    private Compra compra;

    @Builder
    public CuponCliente(Cliente cliente, Cupon cupon) {
        this.cliente = cliente;
        this.cupon = cupon;
    }
}
