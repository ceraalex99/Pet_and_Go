package entities.KeysComposites;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MascotaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "emailusuario")
    private String emailUsuario;

    public MascotaPK(String nombre, String emailUsuario){
        this.nombre = nombre;
        this.emailUsuario = emailUsuario;
    }

    public MascotaPK(){}

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "MascotaPK{" +
                "nombre='" + nombre + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MascotaPK mascotaPK = (MascotaPK) o;
        return Objects.equals(nombre, mascotaPK.nombre) &&
                Objects.equals(emailUsuario, mascotaPK.emailUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, emailUsuario);
    }
}