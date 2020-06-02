package api.services;

import entities.Avatar;

import java.util.List;

public interface AvatarServices {
    boolean altaAvatar(Avatar avatar);
    boolean deleteAvatarById(Integer id);
    void deleteAvatar(Avatar avatar);
    void updateAvatar(Avatar avatar);
    List<Avatar> findAllAvatar();
    Avatar findById(Integer id);
}