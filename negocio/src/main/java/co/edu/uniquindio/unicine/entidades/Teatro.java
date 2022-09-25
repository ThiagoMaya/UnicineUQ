package co.edu.uniquindio.unicine.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Teatro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTeatro;

    private String nombre;

    private String ciudad ;

    private String direccion;

    public Teatro() {
    }

    public Teatro(Integer idTeatro, String nombre, String ciudad, String direccion) {
        this.idTeatro = idTeatro;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public Integer getIdTeatro() {
        return idTeatro;
    }

    public void setIdTeatro(Integer idTeatro) {
        this.idTeatro = idTeatro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teatro)) return false;
        Teatro teatro = (Teatro) o;
        return Objects.equals(idTeatro, teatro.idTeatro) && Objects.equals(nombre, teatro.nombre) && Objects.equals(ciudad, teatro.ciudad) && Objects.equals(direccion, teatro.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTeatro, nombre, ciudad, direccion);
    }
}
