package application.services;

import domain.models.Avatar;

import java.util.List;

public interface AvatarServices {
    boolean altaAvatar(Avatar avatar);

    List<Avatar> findAllAvatar();

    Avatar findById(Integer id);
}