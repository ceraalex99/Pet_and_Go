package api.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MascotaDTO {

    private String nombre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private String emailAmo;

    public MascotaDTO() {
    }

    public MascotaDTO(String nombre,LocalDate fechaNacimiento,String emailAmo) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.emailAmo = emailAmo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public String getEmailAmo() {
        return emailAmo;
    }

    public void setEmailAmo(String emailAmo) {
        this.emailAmo = emailAmo;
    }
}
