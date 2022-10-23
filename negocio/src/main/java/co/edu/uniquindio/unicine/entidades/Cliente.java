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


    @EqualsAndHashCode.Include
    @Id
    private Integer cedula;

    @Column(length = 100, nullable = false)
    private String nombre;

    @NonNull
    @Column(length = 100, nullable = false, unique = true)
    @Email
    private String correo;

    @ElementCollection
    private List<String> telefonos;

    @ToString.Exclude
    @Column(length = 16, nullable = false)
    private String contraseña;

    private String foto_url;

    @Column(nullable = false)
    private boolean estado;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponCliente;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @Builder
    public Cliente( Integer cedula, String nombre, String correo, List<String> telefonos, String contraseña) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.telefonos = telefonos;
        this.contraseña = contraseña;
    }
}
