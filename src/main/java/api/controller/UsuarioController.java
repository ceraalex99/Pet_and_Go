package api.controller;

import api.dto.LoginBody;
import api.dto.UsuarioDTO;
import api.dto.UsuarioUpdateDTO;
import api.services.UsuarioServices;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException; 
import java.security.spec.InvalidKeySpecException;
import java.util.List;


import static io.github.ceraalex99.petandgo.GestorUsuarios.*;


@RestController
@RequestMapping(value="/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

    //UPDATE
    @PutMapping(value= "/{email}")
    public ResponseEntity updateUsuario(@RequestBody UsuarioUpdateDTO usuarioUpdateDTO, @PathVariable(name="email") String email,
                                        @RequestHeader(name="Authorization",required = false) String token) throws InvalidKeySpecException, NoSuchAlgorithmException {

        try{
            if(!decodeJWT(token).equals(usuarioUpdateDTO.getEmail())){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }


        if(!login(usuarioUpdateDTO.getEmail(), usuarioUpdateDTO.getOldPassword())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        signUp(usuarioUpdateDTO.getNombre(), usuarioUpdateDTO.getUsername(), usuarioUpdateDTO.getNewPassword(), usuarioUpdateDTO.getEmail());
        return new ResponseEntity(HttpStatus.OK);

    }
    // - Get todos los Usuarios
    @GetMapping(value= "")
    public ResponseEntity getUsuarios(@RequestHeader(name = "Authorization", required = false) String token ) {
        try{
            decodeJWT(token);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
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
            return new ResponseEntity(HttpStatus.NOT_FOUND);
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

        Usuario usuarioExsitente = usuarioServices.findByEmail(user.getEmail());
        if(usuarioExsitente==null) {
            if(usuarioServices.findByUsername(user.getUsername()) != null){
                return new ResponseEntity("username", HttpStatus.BAD_REQUEST);
            }
            signUp(user.getNombre(),user.getUsername(),user.getPassword(),user.getEmail()); //Llamada a gestorUsuarios
            String token = createToken(user.getEmail());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HEADER_AUTHORIZATION_KEY,token);
            return new ResponseEntity(httpHeaders,HttpStatus.CREATED);
        }
        return new ResponseEntity("email", HttpStatus.BAD_REQUEST);
    }
    //LOGIN
    @PostMapping(value= "/login")
    public ResponseEntity loginRequest(@RequestBody LoginBody login) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if(login(login.getEmail(),login.getPassword())){ // Llamada a gestorUsuarios
            String token = createToken(login.getEmail());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HEADER_AUTHORIZATION_KEY, token);
            ResponseEntity response = new ResponseEntity(headers, HttpStatus.OK);
            return response;
        }
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
    //DELETE USER
    @DeleteMapping(value = "/{email}")
    public ResponseEntity deleteUsuario(@PathVariable(name="email") String email, @RequestHeader(name="Authorization", required = false) String token){
        if(email == null || email.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        try {
            if (!decodeJWT(token).equals(email)) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        if(usuarioServices.findByEmail(email) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
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


}