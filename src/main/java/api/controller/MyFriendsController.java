package api.controller;

import api.services.MyFriendsServices;
import api.services.UsuarioServices;
import entities.MyFriends;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.github.ceraalex99.petandgo.GestorUsuarios.decodeJWT;

@RestController
@RequestMapping(value="/api/amigos")
public class MyFriendsController {

    @Autowired
    @Qualifier("myfriendsservices")
    private MyFriendsServices myfriendsServices;

    @Autowired
    @Qualifier("usuarioservices")
    private UsuarioServices usuarioServices;


    @PostMapping(value = "/{email}/Addamic" )
    public ResponseEntity<Void> addamic(@PathVariable(name="email") String email, @RequestBody String friend,
                                        @RequestHeader(name="Authorization", required = false) String token){
        if(email == null || email.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(friend == null || friend.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            if (!decodeJWT(token).equals(email)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Usuario user = usuarioServices.findByEmail(email);
        Usuario userAmic = usuarioServices.findByEmail(friend);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (userAmic == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        else {
            MyFriends amistat = new MyFriends(user, userAmic);
            myfriendsServices.altaMyFriends(amistat);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
