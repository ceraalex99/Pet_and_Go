package application.services;

import domain.models.MyFriends;
import domain.models.MyFriendsId;

public interface MyFriendsServices {
    void altaMyFriends(MyFriends myFriends);

    boolean existeRelacion(MyFriendsId myFriendsId);

    MyFriends getRelacion(MyFriendsId myFriendsId);

    void deleteMyFriends(MyFriends myFriends);
}