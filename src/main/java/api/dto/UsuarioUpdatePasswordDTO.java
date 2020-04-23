package api.dto;

public class UsuarioUpdatePasswordDTO {
    private String nombre;

    private String username;

    private String newPassword;

    private String oldPassword;


    public UsuarioUpdatePasswordDTO() {
    }

    public UsuarioUpdatePasswordDTO(String nombre, String username, String newPassword, String oldPassword) {
        this.nombre = nombre;
        this.username = username;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
