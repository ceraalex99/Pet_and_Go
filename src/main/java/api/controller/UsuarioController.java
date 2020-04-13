package api.controller;

import api.dto.MascotaDTO;
import api.dto.UsuarioDTO;
import api.services.MascotaServices;
import api.services.UsuarioServices;
import entities.Mascota;
import entities.MascotaId;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static io.github.ceraalex99.petandgo.GestorUsuarios.login;
import static io.github.ceraalex99.petandgo.GestorUsuarios.signUp;


@RestController
@RequestMapping(value="/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;
    @Autowired
    private MascotaServices mascotaServices;

    // - Get todos los Usuarios
    @GetMapping(value= "")
    public ResponseEntity getUsuarios( ) {
        List<Usuario> usuarios = usuarioServices.findAllUsuario();
        if(usuarios==null ) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
        }
    }
    //READ USER
    @GetMapping(value= "/{email}")
    public ResponseEntity getUsuarioByEmail(@PathVariable(name="email") String email){
        Usuario usuario= usuarioServices.findByEmail(email);
        if(usuario==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
    }
    //CREATE USER
    @PostMapping(value= "")
    public ResponseEntity addUsuario(@RequestBody UsuarioDTO userDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Usuario user = new Usuario();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setNombre(userDTO.getNombre());

/*
        if(userDTO==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

 */
        Usuario usuarioExsitente = usuarioServices.findByEmail(user.getEmail());
        if(usuarioExsitente==null) {
            if(usuarioServices.findByUsername(user.getUsername()) != null){
                return new ResponseEntity("username", HttpStatus.BAD_REQUEST);
            }
            signUp(user.getNombre(),user.getUsername(),user.getPassword(),user.getEmail()); //Llamada a gestorUsuarios
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity("email", HttpStatus.BAD_REQUEST);
    }
    //LOGIN
    @PostMapping(value= "/login")
    public ResponseEntity loginRequest(@RequestBody UsuarioDTO userDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Usuario user = new Usuario();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setNombre(userDTO.getNombre());

        Usuario userbd= usuarioServices.findByEmail(user.getEmail());
        if( userbd != null){
            if(login(user.getEmail(),user.getPassword())){ // Llamada a gestorUsuarios
                return new ResponseEntity(userbd, HttpStatus.OK);
            }
            else return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    //DELETE USER
    @DeleteMapping(value = "/{email}")
    public ResponseEntity deleteUsuario(@PathVariable(name="email") String email){
        if(email == null || email.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            boolean delete = usuarioServices.deleteUsuarioByEmail(email);
            if(delete){
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    //READ MASCOTAS
    @GetMapping(value="/{email}/mascotas")
    public ResponseEntity getMascotasUsuario(@PathVariable(name="email") String email){
        if(email==null || email.isEmpty()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            Usuario usuario = usuarioServices.findByEmail(email);
            if(usuario==null ) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            else {
                return new ResponseEntity(usuario.getMascotas(), HttpStatus.OK);
            }
        }
    }
    //CREATE MASCOTA
    @PostMapping(value="/{email}/mascotas")
    public ResponseEntity addMascotaUsuario(@RequestBody MascotaDTO mascotaDTO){
        Usuario amo = usuarioServices.findByEmail(mascotaDTO.getId().getAmo());
        Mascota mascota = new Mascota();
        mascota.setId(new MascotaId(mascotaDTO.getId().getNombre(),mascotaDTO.getId().getAmo()));
        mascota.setFechaNacimiento(mascotaDTO.getFechaNacimiento());
        mascotaServices.altaMascota(mascota);
        amo.addMascota(mascota);
        usuarioServices.updateUsuario(amo);

        return new ResponseEntity( HttpStatus.CREATED);
    }
}