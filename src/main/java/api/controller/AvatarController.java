package api.controller;

import api.dto.AvatarDTO;
import api.services.AvatarServices;
import api.services.UsuarioServices;
import entities.Avatar;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

import java.util.List;

import static io.github.ceraalex99.petandgo.GestorUsuarios.decodeJWT;

@RestController
@RequestMapping(value="/api/avatares")
public class AvatarController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private AvatarServices avatarServices;

    //READ ALL
    @GetMapping(value="")
    public ResponseEntity<List<Avatar>> getAllAvatar(){
        return new ResponseEntity<>(avatarServices.findAllAvatar(), HttpStatus.OK);
    }
    //CREATE
    @PostMapping(value="")
    public ResponseEntity<Avatar> addAvatar(@RequestBody AvatarDTO avatarDTO){
        Avatar avatar = new Avatar();
        avatar.setNiveldesbloqueo(avatarDTO.getNiveldesbloqueo());
        avatar.setAvatar(avatarDTO.getAvatar());
        //////////////////////////////////////////////////////////////////
        try {
            avatarServices.altaAvatar(avatar);
    }
        catch(PersistenceException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(avatar, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{email}/avatar" )
    public ResponseEntity<Void> addAvatar(@PathVariable(name="email") String email, @RequestBody String avatar,
                                   @RequestHeader(name="Authorization", required = false) String token){

        try {
            if (!decodeJWT(token).equals(email)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Usuario user = usuarioServices.findByEmail(email);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            user.setAvatar(avatar);
            usuarioServices.updateUsuario(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}