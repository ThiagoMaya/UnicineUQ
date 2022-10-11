package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private LocalDateTime fechaVencimiento;

    @Positive
    @Column(nullable = false)
    private float valor;

    @Column(nullable = false)
    private String criterio;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "cupon")
    private List<CuponCliente> cuponClientes;

    @Builder
    public Cupon(LocalDateTime fechaVencimiento, float valor, String criterio, String descripcion) {
        this.fechaVencimiento = fechaVencimiento;
        this.valor = valor;
        this.criterio = criterio;
        this.descripcion = descripcion;
    }
}
