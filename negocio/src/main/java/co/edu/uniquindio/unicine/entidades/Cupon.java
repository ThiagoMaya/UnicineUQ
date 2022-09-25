package co.edu.uniquindio.unicine.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Cupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCupon;

    private String descripicon;

    private Date fechaVencimiento;

    private float valor;

    private String criterio;

    public Cupon() {
    }

    public Cupon(Integer idCupon, String descripicon, Date fechaVencimiento, float valor, String criterio) {
        this.idCupon = idCupon;
        this.descripicon = descripicon;
        this.fechaVencimiento = fechaVencimiento;
        this.valor = valor;
        this.criterio = criterio;
    }

    public Integer getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(Integer idCupon) {
        this.idCupon = idCupon;
    }

    public String getDescripicon() {
        return descripicon;
    }

    public void setDescripicon(String descripicon) {
        this.descripicon = descripicon;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cupon)) return false;
        Cupon cupon = (Cupon) o;
        return Float.compare(cupon.valor, valor) == 0 && Objects.equals(idCupon, cupon.idCupon) && Objects.equals(descripicon, cupon.descripicon) && Objects.equals(fechaVencimiento, cupon.fechaVencimiento) && Objects.equals(criterio, cupon.criterio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCupon, descripicon, fechaVencimiento, valor, criterio);
    }
}
