package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CompraConfiteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Float precio;

    @Column( nullable = false)
    private Integer unidades;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Confiteria confiteria;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra compra;

    @Builder
    public CompraConfiteria(Float precio, Integer unidades, Confiteria confiteria, Compra compra) {
        this.precio = precio;
        this.unidades = unidades;
        this.confiteria = confiteria;
        this.compra = compra;
    }

    public int actualizarUnidadesCompradas(char operacion) {
        if(operacion == '-') unidades--;
        if(operacion == '+') unidades++;

        return unidades;
    }
}