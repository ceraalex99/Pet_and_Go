package api.dto;


import java.util.Date;

public class EventoDTO {


    private String titulo;

    private String usuario;

    private Date fecha;

    private Date fechaFin;

    private String descripcion;

    private Boolean notificaciones;

    public EventoDTO() {
    }

    public EventoDTO(String titulo,String usuario,Date fecha,Date fechaFin, String  descripcion) {
        this.titulo=titulo;
        this.usuario=usuario;
        this.fecha=fecha;
        this.fechaFin=fechaFin;
        this.descripcion = descripcion;
    }

    public Boolean getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(Boolean notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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
}
