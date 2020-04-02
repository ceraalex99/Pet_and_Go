package api.dto;

public class MascotaDTO {

    private String nombre;

    private int edad;

    private String emailAmo;

    public MascotaDTO() {
    }

    public MascotaDTO(String nombre,int edad,String emailAmo) {
        this.nombre = nombre;
        this.edad = edad;
        this.emailAmo = emailAmo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmailAmo() {
        return emailAmo;
    }

    public void setEmailAmo(String emailAmo) {
        this.emailAmo = emailAmo;
    }
}
