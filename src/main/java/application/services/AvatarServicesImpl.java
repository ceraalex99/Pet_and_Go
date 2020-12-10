package application.services;

import infrastructure.dao.AvatarDAO;
import domain.models.Avatar;
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
    public List<Avatar> findAllAvatar() {
        return avatarDAO.findAll();
    }

    @Override
    public Avatar findById(Integer id) {
        Avatar           avatar       = null;
        Optional<Avatar> resultAvatar = avatarDAO.findById(id);
        if (resultAvatar.isPresent()) avatar = resultAvatar.get();
        return avatar;
    }
}
