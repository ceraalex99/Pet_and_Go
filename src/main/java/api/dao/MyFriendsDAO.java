package api.dao;

import entities.MyFriends;
import entities.MyFriendsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("myfriendsrepository")
public interface MyFriendsDAO extends JpaRepository<MyFriends, MyFriendsId> {
}
