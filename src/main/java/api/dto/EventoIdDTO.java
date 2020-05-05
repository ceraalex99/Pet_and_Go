package api.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;

public class EventoIdDTO implements Serializable {

    private String titulo;
    private String usuario;
    private Date fecha;

    public EventoIdDTO(){
    }

    public EventoIdDTO(String titulo, String usuario, Date fecha) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventoIdDTO)) return false;
        EventoIdDTO that = (EventoIdDTO) o;
        return Objects.equals(getAmo(), that.getAmo()) &&
                Objects.equals(getNombre(), that.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getAmo());
    }*/
}
