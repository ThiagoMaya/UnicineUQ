package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@ToString
public class Cliente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String cedula;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false, unique = true)
    @Email
    private String email;

    @ElementCollection
    private List<String> telefonos;

    @Column(length = 16, nullable = false)
    private String contraseña;

    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @Builder
    public Cliente(String cedula, String nombre, String email, List<String> telefonos, String contraseña) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefonos = telefonos;
        this.contraseña = contraseña;
    }
}
