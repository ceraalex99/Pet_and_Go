package domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name= "avatares"  )
public class Avatar implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="nivel_desbloqueo")
    private Integer niveldesbloqueo;

    @NotNull
    @Column(name="avatar",unique = true)
    private String avatar;


    public Avatar() {
    }

    public Avatar(String avatar, Integer niveldesbloqueo) {
        this.avatar = avatar;
        this.niveldesbloqueo = niveldesbloqueo;
    }

    public void setNiveldesbloqueo(Integer niveldesbloqueo) {
        this.niveldesbloqueo = niveldesbloqueo;
    }

    public Integer getNiveldesbloqueo() {
        return niveldesbloqueo;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}