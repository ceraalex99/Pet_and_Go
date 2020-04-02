package entities;

import java.io.Serializable;

public class MascotaId implements Serializable {

    private String nombre;
    private Usuario amo;

    public MascotaId(String nombre, Usuario amo) {
        this.nombre = nombre;
        this.amo = amo;
    }
}
