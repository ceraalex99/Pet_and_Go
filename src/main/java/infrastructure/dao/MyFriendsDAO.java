package infrastructure.dao;

import domain.models.MyFriends;
import domain.models.MyFriendsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("myfriendsrepository")
public interface MyFriendsDAO extends JpaRepository<MyFriends, MyFriendsId> {
}
