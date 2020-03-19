package api.controller;

import Entities.Usuario;
import api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/v1")
@Controller
public class UsuarioController {
    @Autowired
    UsuarioServices usuarioServices;

    // - Get todos los Usuarios
    @RequestMapping(value="/usuarios", method= RequestMethod.GET, headers="Accept= application/json")
    public ResponseEntity<List<Usuario>> getUsuarios( ) {
        {
            List<Usuario> usuarios = usuarioServices.findAllUsuario();
            if(usuarios==null ) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
            }
        }
    }
}
