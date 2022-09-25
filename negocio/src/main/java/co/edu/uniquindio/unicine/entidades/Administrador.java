package co.edu.uniquindio.unicine.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Administrador implements Serializable {
    @Id
    private String cedula;
    private String nombre;
    private String email;

    private String numeroTelefonico;

    private String contraseña;
    public Administrador() {}

    public Administrador(String cedula, String nombre, String email, String numeroTelefonico, String contraseña) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.numeroTelefonico = numeroTelefonico;
        this.contraseña = contraseña;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCedula () {
        return cedula;
    }

    public void setCedula (String cedula){
        this.cedula = cedula;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrador)) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(cedula, that.cedula) && Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email) && Objects.equals(numeroTelefonico, that.numeroTelefonico) && Objects.equals(contraseña, that.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, nombre, email, numeroTelefonico, contraseña);
    }
}
