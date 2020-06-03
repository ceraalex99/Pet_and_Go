package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "myfriends")
public class MyFriends {

    @EmbeddedId
    private MyFriendsId id;

    @Column
    private int estado;

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

    @Embeddable
    public static class MyFriendsId implements Serializable {
        public MyFriendsId() {
        }

        public MyFriendsId(String meId, String myFriendId) {
            this.meId = meId;
            this.myFriendId = myFriendId;
        }

        @Column(name = "ME_ID", nullable = false)
        private String meId;

        @Column(name = "MY_FRIEND_ID", nullable = false)
        private String myFriendId;

        public boolean equals(Object o) {
            if (o == null)
                return false;

            if (!(o instanceof MyFriendsId))
                return false;

            MyFriendsId other = (MyFriendsId) o;
            if (!(other.getMeId().equals(getMeId())))
                return false;

            if (!(other.getMyFriendId().equals(getMyFriendId())))
                return false;

            return true;
        }



        private String getMeId() {
            return meId;
        }

        private void setMeId(String meId) {
            this.meId = meId;
        }

        private String getMyFriendId() {
            return myFriendId;
        }

        private void setMyFriendId(String myFriendId) {
            this.myFriendId = myFriendId;
        }
    }
}

