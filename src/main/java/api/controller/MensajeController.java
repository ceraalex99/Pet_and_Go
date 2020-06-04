package api.controller;


import api.dto.MensajeDTO;
import api.services.MensajeServices;
import api.services.UsuarioServices;
import entities.Mensaje;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

import java.util.ArrayList;
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
    public ResponseEntity<Void> addMessage(@RequestBody MensajeDTO mensajeDTO){
        Usuario sender = usuarioServices.findByEmail(mensajeDTO.getSender());
        Usuario receiver = usuarioServices.findByEmail(mensajeDTO.getReceiver());

        Mensaje mensaje = new Mensaje(sender, receiver, mensajeDTO.getText(), mensajeDTO.getCreated_at());

        try {
            mensajeServices.altaMensaje(mensaje);
        }
        catch(PersistenceException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value="/usuarios/{email}/mensajes/{amigo}")
    public ResponseEntity<List<MensajeDTO>> getMensajes(@PathVariable(name = "email") String email,
                                      @RequestHeader(name="Authorization",required = false) String token, @PathVariable(name="amigo") String amigo){

        try{
            if(!decodeJWT(token).equals(email)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Usuario user = usuarioServices.findByEmail(email);

        List<Mensaje> mensajes = mensajeServices.findBySender(user);

        mensajes.addAll(mensajeServices.findByReceiver(user));
        List<MensajeDTO> mensajeDTOList = new ArrayList<>();
        for (Mensaje mensaje : mensajes) {
            mensajeDTOList.add(new MensajeDTO(mensaje.getId(), mensaje.getSender().getEmail(), mensaje.getReceiver().getEmail(), mensaje.getText(), mensaje.getCreated_at()));
        }
        mensajeDTOList.removeIf(mensajeDTO -> !mensajeDTO.getSender().equals(amigo) && !mensajeDTO.getReceiver().equals(amigo));
        mensajeDTOList.sort(Comparator.comparing(MensajeDTO::getCreated_at));

        return new ResponseEntity<>(mensajeDTOList, HttpStatus.OK);


    }
}
