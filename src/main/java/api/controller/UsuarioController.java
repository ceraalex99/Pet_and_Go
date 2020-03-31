package api.controller;

import api.services.UsuarioServices;
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

    // - Get todos los Usuarios
    @GetMapping(value= "")
    public ResponseEntity getUsuarios( ) {
        List<Usuario> usuarios = usuarioServices.findAllUsuario();
        if(usuarios==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
        }
    }

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

    @PostMapping(value= "")
    public ResponseEntity addUsuario(@RequestBody Usuario user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if(user==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Usuario usuarioExsitente = usuarioServices.findByEmail(user.getEmail());
        if(usuarioExsitente==null) {
            if(usuarioServices.findByUsername(user.getUsername()) != null){
                return new ResponseEntity("Username en uso", HttpStatus.BAD_REQUEST);
            }
            signUp(user.getNombre(),user.getUsername(),user.getPassword(),user.getEmail()); //Llamada a gestorUsuarios
            return new ResponseEntity("Usuario creado con exito", HttpStatus.OK);
        }
        return new ResponseEntity("Email en uso", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value= "/login")
    public ResponseEntity loginRequest(@RequestBody Usuario user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Usuario userbd= usuarioServices.findByEmail(user.getEmail());
        if( userbd != null){
            if(login(user.getEmail(),user.getPassword())){ // Llamada a gestorUsuarios
                return new ResponseEntity(userbd, HttpStatus.OK);
            }
            else return new ResponseEntity("Password incorrecto", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity("El email no existe",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity deleteUsuario(@PathVariable(name="email") String email){
        if(email == null || email.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(usuarioServices.deleteUsuarioByEmail(email), HttpStatus.OK);
        }
    }
}