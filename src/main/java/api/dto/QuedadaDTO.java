package api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


public class QuedadaDTO {

    private String admin;


    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaQuedada;

    private String lugarInicio;

    private String lugarFin;


    public QuedadaDTO() {
    }

    public QuedadaDTO(String admin, LocalDateTime fechaQuedada, String lugarInicio, String lugarFin) {
        this.admin = admin;
        this.fechaQuedada = fechaQuedada;
        this.lugarInicio = lugarInicio;
        this.lugarFin = lugarFin;
    }



    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public LocalDateTime getFechaQuedada() {
        return fechaQuedada;
    }

    public void setFechaQuedada(LocalDateTime fechaQuedada) {
        this.fechaQuedada = fechaQuedada;
    }

    public String getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(String lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public String getLugarFin() {
        return lugarFin;
    }

    public void setLugarFin(String lugarFin) {
        this.lugarFin = lugarFin;
    }
}
