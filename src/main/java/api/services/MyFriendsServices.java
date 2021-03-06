package api.services;

import entities.MyFriends;
import entities.MyFriendsId;

public interface MyFriendsServices {
    void altaMyFriends(MyFriends myFriends);
    boolean existeRelacion(MyFriendsId myFriendsId);
    MyFriends getRelacion(MyFriendsId myFriendsId);
    void deleteMyFriends(MyFriends myFriends);
}