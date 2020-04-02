package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "mascotas"  )
@IdClass(MascotaId.class)
public class Mascota implements Serializable {


    @Id
    @Column(name="nombre")
    private String nombre;

    @Column(name="edad")
    private int edad;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="emailAmo")
    private Usuario amo;

    public Mascota() {
    }

    public Mascota(String nombre,int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Usuario getAmo() {
        return amo;
    }

    public void setAmo(Usuario amo) {
        this.amo = amo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", nombre='" + nombre + '\'' +
                ", edad='" + edad + '\'' +
                ", emailAmo='" + amo.getEmail() + '\'' +
                '}';
    }
}