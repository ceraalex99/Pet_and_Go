package api.services;

import entities.Avatar;
import entities.Consejo;

import java.util.List;

public interface AvatarServices {
    boolean altaAvatar(Avatar avatar);
    boolean deleteAvatarById(Integer id);
    void deleteAvatar(Avatar avatar);
    void updateAvatar(Avatar avatar);
    List findAllAvatar();
    Avatar findById(Integer id);
}