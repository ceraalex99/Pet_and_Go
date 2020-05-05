package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class CalendarioId implements Serializable {

    @NotNull
    @Column(name="titulo")
    private String titulo;

    @NotNull
    @Column(name="usuario")
    private String user;

    @NotNull
    @Column(name="fecha")
    private Date fecha;


    public CalendarioId(){
    }

    public CalendarioId(String titulo, String user, Date fecha) {
        this.titulo = titulo;
        this.user = user;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUser() {
        return user;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalendarioId)) return false;
        CalendarioId that = (CalendarioId) o;
        return Objects.equals(getTitulo(), that.getTitulo()) &&
                Objects.equals(getUser(), that.getUser()) && Objects.equals(getFecha(), that.getFecha()) ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getUser(),getFecha());
    }

}
