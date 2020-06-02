package api.services;

import api.dao.AvatarDAO;
import entities.Avatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("avatarServices")
@Transactional
public class AvatarServicesImpl implements AvatarServices {

    @Autowired
    AvatarDAO avatarDAO;

    @Override
    public boolean altaAvatar(Avatar avatar) {
        return avatarDAO.save(avatar) != null;
    }

    @Override
    public boolean deleteAvatarById(Integer id) {
        avatarDAO.deleteById(id);
        return !avatarDAO.findById(id).isPresent();
    }

    @Override
    public void deleteAvatar(Avatar avatar) {
        avatarDAO.delete(avatar);
    }

    @Override
    public void updateAvatar(Avatar avatar) {
        avatarDAO.save(avatar);
    }

    @Override
    public List<Avatar> findAllAvatar() {
        return avatarDAO.findAll();
    }

    @Override
    public Avatar findById(Integer id) {
        Avatar avatar = null;
        Optional<Avatar> resultAvatar = avatarDAO.findById(id);
        if (resultAvatar.isPresent()) avatar = resultAvatar.get();
        return avatar;
    }
}
