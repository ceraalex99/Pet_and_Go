package domain.dto;

public class UsuarioUpdateCamposDTO {
    private String nombre;

    private String username;


    public UsuarioUpdateCamposDTO() {
    }

    public UsuarioUpdateCamposDTO(String nombre, String username) {
        this.nombre = nombre;
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
