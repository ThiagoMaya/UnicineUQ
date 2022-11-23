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
public class Cliente extends Persona implements Serializable {


    @ElementCollection
    private List<String> telefonos;

    private String foto_url = "";

    @Column(nullable = false)
    private boolean estado = false;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponCliente;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @Builder

    public Cliente(Integer cedula, String nombre, String email, String contrasena, List<String> telefonos, String foto_url, boolean estado) {
        super(cedula, nombre, email, contrasena);
        this.telefonos = telefonos;
        this.foto_url = foto_url;
        this.estado = estado;
    }
}
