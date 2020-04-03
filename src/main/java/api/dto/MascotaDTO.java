package api.dto;

import java.util.Date;

public class MascotaDTO {

    private String nombre;

    private String fechaNacimiento;

    private String emailUsuario;

    public MascotaDTO() {
    }

    public MascotaDTO(String nombre,String emailUsuario,String fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.emailUsuario = emailUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setEdad(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
}
