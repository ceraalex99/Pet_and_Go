package api.controller;

import api.dto.MascotaDTO;
import api.services.MascotaServices;
import api.services.UsuarioServices;
import entities.Mascota;
import entities.MascotaId;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

import static io.github.ceraalex99.petandgo.GestorUsuarios.decodeJWT;

@RestController
@RequestMapping(value="/api/usuarios/{email}/mascotas")
public class MascotaController {
    @Autowired
    private UsuarioServices usuarioServices;
    @Autowired
    private MascotaServices mascotaServices;

    //READ ALL
    @GetMapping(value="")
    public ResponseEntity getMascotasUsuario(@PathVariable(name="email") String email){
        if(email==null || email.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else{
            Usuario usuario = usuarioServices.findByEmail(email);
            if(usuario==null ) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity(usuario.getMascotas(), HttpStatus.OK);
            }
        }
    }

    //READ
    @GetMapping(value="{nombre}")
    public ResponseEntity getMascotaUsuario(@PathVariable(name="email") String email, @PathVariable(name="nombre") String nombre){
        if(email==null || email.isEmpty() || nombre==null || nombre.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else{
            MascotaId mascotaId = new MascotaId(nombre, email);
            Mascota mascota = mascotaServices.findById(mascotaId);
            if(mascota==null ) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity(mascota, HttpStatus.OK);
            }
        }
    }


    //CREATE
    @PostMapping(value="")
    public ResponseEntity addMascotaUsuario(@RequestBody MascotaDTO mascotaDTO,
                                            @RequestHeader(name="Authorization",required = false) String token){

        try{
            if(!decodeJWT(token).equals(mascotaDTO.getId().getAmo())){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        Mascota mascota = new Mascota();
        mascota.setId(new MascotaId(mascotaDTO.getId().getNombre(),mascotaDTO.getId().getAmo()));
        mascota.setFechaNacimiento(mascotaDTO.getFechaNacimiento());
        try {
            mascotaServices.altaMascota(mascota);
        }
        catch(PersistenceException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping(value= "{nombre}")
    public ResponseEntity updateMascota(@RequestBody MascotaDTO mascotaDTO, @PathVariable(name="email") String email,
                                        @PathVariable(name="nombre") String nombre,
                                        @RequestHeader(name="Authorization",required = false) String token){

        if(!nombre.equals(mascotaDTO.getId().getNombre()) || !email.equals(mascotaDTO.getId().getAmo())){
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

        MascotaId mascotaId = new MascotaId(nombre, email);
        Mascota mascota = mascotaServices.findById(mascotaId);

        if(mascota == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        mascota.setFechaNacimiento(mascotaDTO.getFechaNacimiento());
        mascotaServices.altaMascota(mascota);
        return new ResponseEntity(HttpStatus.OK);

    }

    //DELETE
    @DeleteMapping(value = "{nombre}")
    public ResponseEntity deleteMascota(@PathVariable(name="email") String email, @PathVariable(name="nombre") String nombre,
                                        @RequestHeader(name="Authorization",required = false) String token) {

        if(email==null || email.isEmpty() || nombre == null || nombre.isEmpty()){
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

        MascotaId id = new MascotaId(nombre,email);

        if(mascotaServices.findById(id) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        boolean deleted = mascotaServices.deleteMascotaById(id);
        if(deleted){
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}