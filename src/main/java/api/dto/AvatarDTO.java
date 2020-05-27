package api.dto;

public class AvatarDTO {


    private Integer niveldesbloqueo;

    private String avatar;

    public AvatarDTO() {
    }

    public AvatarDTO(Integer niveldesbloqueo, String URLavatar) {
        this.niveldesbloqueo = niveldesbloqueo;
        this.avatar=URLavatar;
    }

    public Integer getNiveldesbloqueo() {
        return niveldesbloqueo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setNiveldesbloqueo(Integer niveldesbloqueo) {
        this.niveldesbloqueo = niveldesbloqueo;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

