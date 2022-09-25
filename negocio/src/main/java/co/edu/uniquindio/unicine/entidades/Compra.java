package co.edu.uniquindio.unicine.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;

    private Date fechaCompra;

    private String metodo_pago;

    private float subtotal;

    private float total;

    public Compra() {
    }

    public Compra(Integer idCompra, Date fechaCompra, String metodo_pago, float subtotal, float total) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.metodo_pago = metodo_pago;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compra)) return false;
        Compra compra = (Compra) o;
        return Float.compare(compra.subtotal, subtotal) == 0 && Float.compare(compra.total, total) == 0 && Objects.equals(idCompra, compra.idCompra) && Objects.equals(fechaCompra, compra.fechaCompra) && Objects.equals(metodo_pago, compra.metodo_pago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, fechaCompra, metodo_pago, subtotal, total);
    }
}
