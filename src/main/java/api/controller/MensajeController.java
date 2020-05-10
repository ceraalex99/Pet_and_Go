package api.controller;


import api.services.MensajeServices;
import api.services.UsuarioServices;
import entities.Mensaje;
import entities.Quedada;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

import java.util.Comparator;
import java.util.List;

import static io.github.ceraalex99.petandgo.GestorUsuarios.decodeJWT;

@RestController
@RequestMapping(value="/api")
public class MensajeController {

    @Autowired
    private MensajeServices mensajeServices;

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping(value="/mensajes")
    public ResponseEntity addMessage(@RequestBody MensajeDTO mensajeDTO){
        Mensaje mensaje = new Mensaje(mensajeDTO.getSender(), mensajeDTO.getReceiver(), mensajeDTO.getData(), mensajeDTO.getCreated_at());

        try {
            mensajeServices.altaMensaje(mensaje);
        }
        catch(PersistenceException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value="/usuarios/{email}/mensajes")
    public ResponseEntity getMensajes(@PathVariable(name = "email") String email,
                                      @RequestHeader(name="Authorization",required = false) String token){

        try{
            if(!decodeJWT(token).equals(email)){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        Usuario user = usuarioServices.findByEmail(email);

        List<Mensaje> mensajes = mensajeServices.findBySender(user);
        mensajes.addAll(mensajeServices.findByReceiver(user));

        mensajes.sort(Comparator.comparing(Mensaje::getCreated_at));

        return new ResponseEntity(mensajes, HttpStatus.OK);


    }
}