package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name= "eventos"  )
public class Evento implements Serializable {


    @EmbeddedId
    private CalendarioId id;

    @Column(name="descripcion")
    private String descripcion;

    public Evento() {
    }

    public Evento(CalendarioId id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public CalendarioId getId() {
        return id;
    }

    public void setId(CalendarioId id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Evento{" +
                ", titulo='" + id.getTitulo() + '\'' +
                ", user='" + id.getUser() + '\'' +
                ", fecha='" + id.getFecha() + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}