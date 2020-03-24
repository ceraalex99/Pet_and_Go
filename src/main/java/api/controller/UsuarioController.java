package api.controller;

import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping(value= "/{username}")
    public ResponseEntity getUsuarioByUsername(@PathVariable(name="username") String username){
        Usuario usuario= usuarioServices.findByUsername(username);
        if(usuario==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
    }

    @PostMapping(value= "")
    public ResponseEntity addUsuario(@RequestBody Usuario user){

        if(user==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(usuarioServices.altaUsuario(user), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity deleteUsuario(@PathVariable(name="username") String username){

        if(username == null || username.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(usuarioServices.deleteUsuarioByUsername(username), HttpStatus.OK);
        }
    }
}