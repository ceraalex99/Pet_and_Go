package api.services;

import api.dao.MyFriendsDAO;
import entities.MyFriends;
import entities.MyFriendsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("myfriendsservices")
@Transactional
public class MyFriendsServicesImpl implements MyFriendsServices {
    @Autowired
    private MyFriendsDAO myFriendsDAO;

    @Override
    public void altaMyFriends(MyFriends myFriends) {
        myFriendsDAO.save(myFriends);
    }

    @Override
    public boolean ExisteRelacion(MyFriendsId myFriendsId) {
        return myFriendsDAO.findById(myFriendsId).isPresent();
    }

    @Override
    public MyFriends getRelacion(MyFriendsId myFriendsId) {
        MyFriends myFriends = null;
        Optional<MyFriends> opmyfriend = myFriendsDAO.findById(myFriendsId);
        if (opmyfriend.isPresent()) myFriends = opmyfriend.get();
        return  myFriends;
    }
}
