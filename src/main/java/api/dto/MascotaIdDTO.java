package api.dto;

import java.io.Serializable;
import java.util.Objects;

public class MascotaIdDTO implements Serializable {
    private String nombre;
    private String amo;

    public MascotaIdDTO(){
    }

    public MascotaIdDTO(String nombre, String amo) {
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
        if (!(o instanceof MascotaIdDTO)) return false;
        MascotaIdDTO that = (MascotaIdDTO) o;
        return Objects.equals(getAmo(), that.getAmo()) &&
                Objects.equals(getNombre(), that.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getAmo());
    }
}
