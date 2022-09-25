package co.edu.uniquindio.unicine.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;

    private String tipo;

    private String estado;

    public Sala(Integer idSala, String tipo, String estado) {
        this.idSala = idSala;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Sala() {
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sala)) return false;
        Sala sala = (Sala) o;
        return Objects.equals(idSala, sala.idSala) && Objects.equals(tipo, sala.tipo) && Objects.equals(estado, sala.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSala, tipo, estado);
    }
}
