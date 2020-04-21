package api.dto;

public class UsuarioUpdateDTO {
    private String nombre;

    private String username;

    private String newPassword;

    private String email;

    private String oldPassword;


    public UsuarioUpdateDTO() {
    }

    public UsuarioUpdateDTO(String nombre,String username,String newPassword,String email, String oldPassword) {
        this.nombre = nombre;
        this.username = username;
        this.newPassword = newPassword;
        this.email = email;
        this.oldPassword = oldPassword;
    }

    public UsuarioUpdateDTO(String email){
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
