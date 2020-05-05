package api.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EventoDTO {


    private EventoIdDTO id;

    private String descripcion;


    public EventoDTO() {
    }

    public EventoDTO(EventoIdDTO id, String  descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public EventoIdDTO getId() {
        return id;
    }

    public void setId(EventoIdDTO id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
