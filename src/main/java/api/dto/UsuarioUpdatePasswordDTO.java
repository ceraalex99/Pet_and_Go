package api.dto;

public class UsuarioUpdatePasswordDTO {
    private String newPassword;

    private String oldPassword;


    public UsuarioUpdatePasswordDTO() {
    }

    public UsuarioUpdatePasswordDTO(String nombre, String username, String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
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
