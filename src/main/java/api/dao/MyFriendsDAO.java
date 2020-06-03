package api.dao;

import entities.MyFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("myfriendsrepository")
public interface MyFriendsDAO extends JpaRepository<MyFriends, Serializable> {
}
