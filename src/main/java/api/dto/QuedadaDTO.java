package api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class QuedadaDTO {

    private String admin;


    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaQuedada;

    private String lugarInicio;

    private int idIamgeGoogle;

    private double latitud;

    private double longitud;


    public QuedadaDTO() {
    }

    public QuedadaDTO(String admin, Date fechaQuedada, String lugarInicio, int idIamgeGoogle, double latitud, double longitud) {
        this.admin = admin;
        this.fechaQuedada = fechaQuedada;
        this.lugarInicio = lugarInicio;
        this.idIamgeGoogle = idIamgeGoogle;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Date getFechaQuedada() {
        return fechaQuedada;
    }

    public void setFechaQuedada(Date fechaQuedada) {
        this.fechaQuedada = fechaQuedada;
    }

    public String getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(String lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public int getIdIamgeGoogle() {
        return idIamgeGoogle;
    }

    public void setIdIamgeGoogle(int idIamgeGoogle) {
        this.idIamgeGoogle = idIamgeGoogle;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
