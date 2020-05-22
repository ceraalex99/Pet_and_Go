package api.controller;
import java.util.concurrent.ThreadLocalRandom;
import api.dto.ConsejoDTO;
import api.dto.EventoDTO;
import api.services.ConsejoServices;
import api.services.EventoServices;
import api.services.UsuarioServices;
import entities.Consejo;
import entities.Evento;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

import static io.github.ceraalex99.petandgo.GestorUsuarios.decodeJWT;

@RestController
@RequestMapping(value="/api/consejos")
public class ConsejoController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private ConsejoServices consejoServices;


    @GetMapping(value="/one")
    public ResponseEntity getConsejo(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, consejoServices.findAllConsejos().size());
        System.out.println(randomNum);
        return new ResponseEntity(consejoServices.findById(randomNum), HttpStatus.OK);
    }


    //READ ALL CONSEJOS
    @GetMapping(value="")
    public ResponseEntity getAllConsejos(){
        return new ResponseEntity(consejoServices.findAllConsejos(), HttpStatus.OK);
    }
    //CREATE
    @PostMapping(value="")
    public ResponseEntity addConsejo(@RequestBody ConsejoDTO consejoDTO){
        Consejo consejo = new Consejo();
        consejo.setConsejo(consejoDTO.getConsejo());
        //////////////////////////////////////////////////////////////////
        try {
            consejoServices.altaConsejo(consejo);
        }
        catch(PersistenceException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(consejo,HttpStatus.CREATED);
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteConsejo(@PathVariable Integer id) {

        if(id == null ){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(consejoServices.findById(id) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        boolean deleted = consejoServices.deleteConsejoById(id);
        if(deleted){
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}