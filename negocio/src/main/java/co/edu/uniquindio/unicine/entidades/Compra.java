package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    @Positive
    private float total;

    @Positive
    private Integer numeroEntradas;

    @ToString.Exclude
    @OneToMany(mappedBy = "compra")
    private List<CompraConfiteria> compraConfiterias;

    @ToString.Exclude
    @OneToMany(mappedBy = "compra")
    private List<Entrada> entradas;


    @OneToOne
    private CuponCliente cuponCliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Funcion funcion;

    @Builder

    public Compra(MedioPago medioPago, LocalDateTime fechaCompra, float total, CuponCliente cuponCliente, Cliente cliente, Funcion funcion) {
        this.medioPago = medioPago;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.cuponCliente = cuponCliente;
        this.cliente = cliente;
        this.funcion = funcion;
    }
}
