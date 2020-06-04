package api.dto;

import entities.MyFriends;
import entities.Usuario;
import helpers.Relacion;

import java.util.ArrayList;
import java.util.List;

public class UsuarioParticipanteDTO {
    private String nombre;

    private String username;

    private String email;

    private byte[] image;

    private Relacion estado;

    private String urlAvatar;

    public UsuarioParticipanteDTO() {
    }

    public UsuarioParticipanteDTO(Usuario user,Relacion estado) {
        this.nombre = user.getNombre();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.image = user.getImage();
        this.estado = estado;
        this.urlAvatar = user.getAvatar();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Relacion getEstado() {
        return estado;
    }

    public void setEstado(Relacion estado) {
        this.estado = estado;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

}
