package entities;

import javax.persistence.*;

@Entity
@Table(name= "mascotas"  )
public class Mascota  {


    @Id
    @Column(name="nombre")
    private String nombre;

    @Column(name="edad")
    private int edad;

    @Column(name="emailAmo")
    private String emailAmo;

    public Mascota() {
    }

    public Mascota(String nombre,int edad,String emailAmo) {
        this.nombre = nombre;
        this.edad = edad;
        this.emailAmo = emailAmo;
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

    public String getEmailAmo() {
        return emailAmo;
    }

    public void setEmailAmo(String emailAmo) {
        this.emailAmo = emailAmo;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                ", nombre='" + nombre + '\'' +
                ", edad='" + edad + '\'' +
                ", emailAmo='" + emailAmo + '\'' +
                '}';
    }
}