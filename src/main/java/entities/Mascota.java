package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "mascotas"  )
public class Mascota implements Serializable {


    @EmbeddedId
    private MascotaId id;

    @Column(name="edad")
    private int edad;

    public Mascota() {
    }

    public Mascota(MascotaId id,int edad) {
        this.id = id;
        this.edad = edad;
    }

    public MascotaId getId() {
        return id;
    }

    public void setId(MascotaId id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                ", nombre='" + id.getNombre() + '\'' +
                ", edad='" + edad + '\'' +
                ", emailAmo='" + id.getAmo().getEmail() + '\'' +
                '}';
    }
}