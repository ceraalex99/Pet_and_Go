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
@RequestMapping(value="/api/calendario/{email}/eventos")
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
            if(!decodeJWT(token).equals(eventoDTO.getUsuario())){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        Evento evento = new Evento();
        evento.setTitulo(eventoDTO.getTitulo());
        evento.setUsuario(eventoDTO.getUsuario());
        evento.setFecha(eventoDTO.getFecha());
        evento.setFechaFin(eventoDTO.getFechaFin());
        evento.setDescripcion(eventoDTO.getDescripcion());
        //////////////////////////////////////////////////////////////////
        try {
            eventoServices.altaEvento(evento);
        }
        catch(PersistenceException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(evento,HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteEvento(@PathVariable(name="email") String email, @PathVariable Integer id,
                                        @RequestHeader(name="Authorization",required = false) String token) {

        if(email==null || email.isEmpty() || id == null ){
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
    //UPDATE
    @PutMapping(value = "/{id}")
    public ResponseEntity updatePerreParada(@PathVariable(name="email") String email,@RequestBody EventoDTO eventoDTO , @PathVariable(name="id") Integer id,
                                            @RequestHeader(name="Authorization",required = false) String token) {

        if(email==null || email.isEmpty() || id == null ){
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

        Evento evento= eventoServices.findById(id);
        if(evento== null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setTitulo(eventoDTO.getTitulo());
        evento.setFecha(eventoDTO.getFecha());
        evento.setFechaFin(eventoDTO.getFechaFin());

        eventoServices.updateEvento(evento);
        return new ResponseEntity(evento,HttpStatus.OK);

    }
}