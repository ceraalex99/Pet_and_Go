package api.dao;


import entities.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("avatarrepository")
public interface AvatarDAO extends JpaRepository<Avatar, Integer> {

}
