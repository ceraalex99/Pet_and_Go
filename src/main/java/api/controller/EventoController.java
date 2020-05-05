package api.controller;

import api.dto.EventoDTO;
import api.dto.EventoIdDTO;
import api.dto.MascotaDTO;
import api.services.EventoServices;
import api.services.MascotaServices;
import api.services.UsuarioServices;
import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

import static io.github.ceraalex99.petandgo.GestorUsuarios.decodeJWT;

@RestController
@RequestMapping(value="/api/usuarios/{email}/eventos")
public class EventoController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private EventoServices eventoServices;

    //READ ALL EVENTOS
    @GetMapping(value="")
    public ResponseEntity getEventosUsuario(@PathVariable(name="email") String email){
        if(email==null || email.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else{
            Usuario usuario = usuarioServices.findByEmail(email);
            if(usuario==null ) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity(usuario.getEventos(), HttpStatus.OK);
            }
        }
    }
    //CREATE
    @PostMapping(value="")
    public ResponseEntity addEventoUsuario(@RequestBody EventoDTO eventoDTO,
                                            @RequestHeader(name="Authorization",required = false) String token){

        try{
            if(!decodeJWT(token).equals(eventoDTO.getId().getUsuario())){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        Evento evento = new Evento();
        evento.setId(new CalendarioId(eventoDTO.getId().getTitulo(),eventoDTO.getId().getUsuario(),eventoDTO.getId().getFecha()));
        evento.setDescripcion(eventoDTO.getDescripcion());
        //////////////////////////////////////////////////////////////////
        try {
            eventoServices.altaEvento(evento);
        }
        catch(PersistenceException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping(value = "")
    public ResponseEntity deleteEvento(@PathVariable(name="email") String email, @RequestBody EventoIdDTO eventoIdDTO,
                                        @RequestHeader(name="Authorization",required = false) String token) {

        if(email==null || email.isEmpty() || eventoIdDTO.getTitulo() == null || eventoIdDTO.getTitulo().isEmpty()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        try{
            if(!decodeJWT(token).equals(email)){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        CalendarioId id = new CalendarioId(eventoIdDTO.getTitulo(),email,eventoIdDTO.getFecha());

        if(eventoServices.findById(id) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Usuario user = usuarioServices.findByEmail(email);


        user.removeEvento(eventoServices.findById(id));
        boolean deleted = eventoServices.deleteEventoById(id);
        if(deleted){
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}