package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MascotaId implements Serializable {

    @Column(name="nombre")
    private String nombre;

    @Column(name="emailusuario")
    private String amo;

    public MascotaId(){
    }

    public MascotaId(String nombre, String amo) {
        this.nombre = nombre;
        this.amo = amo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAmo() {
        return amo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MascotaId)) return false;
        MascotaId that = (MascotaId) o;
        return Objects.equals(getAmo(), that.getAmo()) &&
                Objects.equals(getNombre(), that.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getAmo());
    }
}
