package entities;

import entities.KeysComposites.MascotaPK;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mascotas")
public class Mascota  {

    @EmbeddedId
    private MascotaPK key;

    public MascotaPK getKey() {
        return key;
    }

    @Column(name="fechanacimiento")
    private String fechaNacimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emailUsuario" ,insertable=false)
    private Usuario usuario;

    public Mascota() {
    }

    public Mascota(String nombre,String fechaNacimiento,String emailUsuario) {
        this.fechaNacimiento = fechaNacimiento;
        this.key = new MascotaPK(nombre,emailUsuario);
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "key=" + key +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
