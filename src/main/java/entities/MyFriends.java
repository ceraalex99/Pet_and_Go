package entities;

import helpers.Relacion;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "myfriends")
public class MyFriends implements Serializable {

    public MyFriendsId getId() {
        return id;
    }

    @EmbeddedId
    private MyFriendsId id;

    public Relacion getEstado() {
        return estado;
    }

    public void setEstado(Relacion estado) {
        this.estado = estado;
    }

    @Column
    private Relacion estado;

    public String getMyFriend() {
        return myFriend.getEmail();
    }

    @ManyToOne
    @JoinColumn(name = "ME_ID", insertable = false, updatable = false)
    private Usuario me;

    @ManyToOne
    @JoinColumn(name = "MY_FRIEND_ID", insertable = false, updatable = false)
    private Usuario myFriend;

    public MyFriends(Usuario email1, Usuario email2) {
        this.id = new MyFriendsId(email1.getEmail(), email2.getEmail());
        this.me = email1;
        this.myFriend = email2;
    }

    public MyFriends() {
    }
}

