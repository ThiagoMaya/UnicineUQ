package co.edu.uniquindio.unicine.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Cliente implements Serializable {

    @Id
    private String cedula;
    private String nombre;
    private String email;

    private String numeroTelefonico;

    private String contraseña;
    public Cliente() {}

    public Cliente(String cedula, String nombre, String email, String numeroTelefonico, String contraseña) {
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
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cedula, cliente.cedula) && Objects.equals(nombre, cliente.nombre) && Objects.equals(email, cliente.email) && Objects.equals(numeroTelefonico, cliente.numeroTelefonico) && Objects.equals(contraseña, cliente.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, nombre, email, numeroTelefonico, contraseña);
    }
}
