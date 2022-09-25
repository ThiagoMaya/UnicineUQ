package co.edu.uniquindio.unicine.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;

@Entity
public class Funcion implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncion;

    private Date fecha;

    private String hora;

    private String tipo;

    private String estado;

    private float precio;

    public Funcion() {
    }

    public Funcion(Integer idFuncion, Date fecha, String hora, String tipo, String estado, float precio) {
        this.idFuncion = idFuncion;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.estado = estado;
        this.precio = precio;
    }

    public Integer getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(String Integer) {
        this.idFuncion = idFuncion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcion)) return false;
        Funcion funcion = (Funcion) o;
        return Float.compare(funcion.precio, precio) == 0 && Objects.equals(idFuncion, funcion.idFuncion) && Objects.equals(fecha, funcion.fecha) && Objects.equals(hora, funcion.hora) && Objects.equals(tipo, funcion.tipo) && Objects.equals(estado, funcion.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFuncion, fecha, hora, tipo, estado, precio);
    }
}
