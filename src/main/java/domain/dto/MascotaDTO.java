package domain.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MascotaDTO {



    private MascotaIdDTO id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;


    public MascotaDTO() {
    }

    public MascotaDTO(MascotaIdDTO id,LocalDate fechaNacimiento) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
    }

    public MascotaIdDTO getId() {
        return id;
    }

    public void setId(MascotaIdDTO id) {
        this.id = id;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



}
