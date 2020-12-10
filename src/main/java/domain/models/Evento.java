package domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "eventos"  )
public class Evento implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="titulo",unique = true)
    private String titulo;

    @NotNull
    @Column(name="usuario")
    private String usuario;

    @NotNull
    @Column(name="fecha")
    private Date fecha;

    @NotNull
    @Column(name="fecha_fin")
    private Date fechaFin;

    @Column(name="descripcion")
    private String descripcion;

    @NotNull
    @Column(name="notificaciones")
    private Boolean notificaciones;

    public Evento() {
    }

    public Evento(String titulo,String usuario,Date fecha, Date fechaFin, String descripcion, Boolean notificaciones) {
        this.titulo=titulo;
        this.usuario=usuario;
        this.fecha=fecha;
        this.fechaFin=fechaFin;
        this.descripcion = descripcion;
        this.notificaciones = notificaciones;
    }

    public void setNotificaciones(Boolean notificaciones) {
        this.notificaciones = notificaciones;
    }

    public Boolean getNotificaciones() {
        return notificaciones;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
                ", titulo='" + titulo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", fecha='" + fecha + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}