package co.edu.uniquindio.unicine.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class Silla {

    @Id
    private String idSilla;

    public Silla() {
    }

    public Silla(String idSilla) {
        this.idSilla = idSilla;
    }

    public String getIdSilla() {
        return idSilla;
    }

    public void setIdSilla(String idSilla) {
        this.idSilla = idSilla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Silla)) return false;
        Silla silla = (Silla) o;
        return Objects.equals(idSilla, silla.idSilla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSilla);
    }
}
