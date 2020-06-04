package api.controller;

import api.services.MyFriendsServices;
import api.services.UsuarioServices;
import entities.MyFriends;
import entities.Usuario;
import helpers.Relacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

        if(friend.equals(email)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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
            MyFriends amistatInversa = new MyFriends(userAmic, user);

            if(!myfriendsServices.existeRelacion(amistat.getId())) {
                amistat.setEstado(Relacion.ACEPTADA);
                amistatInversa.setEstado(Relacion.ACEPTADA);
                myfriendsServices.altaMyFriends(amistat);
                myfriendsServices.altaMyFriends(amistatInversa);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }


    @PostMapping(value = "/{email}/Removeamic" )
    public ResponseEntity<Void> removeamic(@PathVariable(name="email") String email, @RequestBody String friend,
                                        @RequestHeader(name="Authorization", required = false) String token){
        if(email == null || email.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(friend == null || friend.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(friend.equals(email)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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
            MyFriends amistatInversa = new MyFriends(userAmic, user);

            if(myfriendsServices.existeRelacion(amistat.getId())) {
                myfriendsServices.deleteMyFriends(amistat);
                myfriendsServices.deleteMyFriends(amistatInversa);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping(value = "/{email}/Removebloqueado" )
    public ResponseEntity<Void> removebloqueado(@PathVariable(name="email") String email, @RequestBody String friend,
                                           @RequestHeader(name="Authorization", required = false) String token){
        if(email == null || email.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(friend == null || friend.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(friend.equals(email)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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
            MyFriends amistatInversa = new MyFriends(userAmic, user);

            MyFriends relacion = myfriendsServices.getRelacion(amistat.getId());
            MyFriends relacion2 = myfriendsServices.getRelacion(amistatInversa.getId());

            if(relacion.getEstado()==Relacion.BLOQUEADO) {
                myfriendsServices.deleteMyFriends(relacion);
                myfriendsServices.deleteMyFriends(relacion2);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping(value = "/{email}/Bloquear" )
    public ResponseEntity<Void> bloquear(@PathVariable(name="email") String email, @RequestBody String friend,
                                             @RequestHeader(name="Authorization", required = false) String token){

        if(friend.equals(email)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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
            MyFriends amistatInversa = new MyFriends(userAmic, user);

            MyFriends relacion = myfriendsServices.getRelacion(amistat.getId());
            MyFriends relacion2 = myfriendsServices.getRelacion(amistatInversa.getId());
            if(relacion != null && relacion2 != null) {
                relacion.setEstado(Relacion.BLOQUEADO);
                relacion2.setEstado(Relacion.BLOQUEA);

                myfriendsServices.altaMyFriends(relacion);
                myfriendsServices.altaMyFriends(relacion2);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                amistat.setEstado(Relacion.BLOQUEADO);
                amistatInversa.setEstado(Relacion.BLOQUEA);
                myfriendsServices.altaMyFriends(amistat);
                myfriendsServices.altaMyFriends(amistatInversa);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
    }

    @GetMapping(value = "/{email}/Bloqueados" )
    public ResponseEntity<List<String>> bloqueados(@PathVariable(name="email") String email){
        if(email == null || email.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Usuario user = usuarioServices.findByEmail(email);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<String> bloqueados = new ArrayList<>();
        for (MyFriends myFriends: user.getMyFriends()){
            if(myFriends.getEstado() == Relacion.BLOQUEADO){
                bloqueados.add(myFriends.getMyFriend());
            }
        }
        return new ResponseEntity<>(bloqueados, HttpStatus.OK);
    }
}