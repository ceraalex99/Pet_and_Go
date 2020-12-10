package domain.dto;

import java.io.Serializable;
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
}
