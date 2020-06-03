package api.services;

import api.dao.MyFriendsDAO;
import entities.MyFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("myfriendsservices")
@Transactional
public class MyFriendsServicesImpl implements MyFriendsServices {
    @Autowired
    private MyFriendsDAO myFriendsDAO;

    @Override
    public void altaMyFriends(MyFriends myFriends) {
        myFriendsDAO.save(myFriends);
    }
}
