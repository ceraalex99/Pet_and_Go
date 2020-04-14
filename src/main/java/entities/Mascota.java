package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name= "mascotas"  )
public class Mascota implements Serializable {


    @EmbeddedId
    private MascotaId id;

    @Column(name="fechanacimiento")
    private LocalDate fechaNacimiento;

    public Mascota() {
    }

    public Mascota(MascotaId id,LocalDate fechaNacimiento) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
    }

    public MascotaId getId() {
        return id;
    }

    public void setId(MascotaId id) {
        this.id = id;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                ", nombre='" + id.getNombre() + '\'' +
                ", fecha de nacimiento='" + fechaNacimiento + '\'' +
                '}';
    }
}
