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
    UsuarioServices usuarioServices;

    // - Get todos los Usuarios
    @RequestMapping(value= "", method = RequestMethod.GET)
    public ResponseEntity getUsuarios( ) {
        List<Usuario> usuarios = usuarioServices.findAllUsuario();
        if(usuarios==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
        }
    }

    @RequestMapping(value= "/{username}", method = RequestMethod.GET)
    public ResponseEntity getUSER(@PathVariable(name="username") String username){
        Usuario usuario= usuarioServices.findByUsername(username);
        if(usuario==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
    }


    @RequestMapping(value= "", method = RequestMethod.POST)
    public ResponseEntity addUSER(@RequestBody Usuario user){
        if(user==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(usuarioServices.altaUsuario(user), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUSER(@PathVariable(name="username") String username){

        if(username == null || username == "") {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(usuarioServices.deleteUsuarioByUsername(username), HttpStatus.OK);
        }
    }






}
