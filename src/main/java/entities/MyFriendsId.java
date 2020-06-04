package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MyFriendsId implements Serializable {
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