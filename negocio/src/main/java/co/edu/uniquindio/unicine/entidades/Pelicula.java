package co.edu.uniquindio.unicine.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Pelicula implements Serializable {

    @Id
    private String nombre;

    private String genero;

    private String sinopsis;

    private String reparto;

    private String estado;

    public Pelicula() {
    }

    public Pelicula(String nombre, String genero, String sinopsis, String reparto, String estado) {
        this.nombre = nombre;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pelicula)) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(nombre, pelicula.nombre) && Objects.equals(genero, pelicula.genero) && Objects.equals(sinopsis, pelicula.sinopsis) && Objects.equals(reparto, pelicula.reparto) && Objects.equals(estado, pelicula.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, genero, sinopsis, reparto, estado);
    }
}
