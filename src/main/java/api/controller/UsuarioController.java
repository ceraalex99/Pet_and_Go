package api.controller;

import api.dto.LoginBody;
import api.dto.UsuarioDTO;
import api.dto.UsuarioUpdateCamposDTO;
import api.dto.UsuarioUpdatePasswordDTO;
import api.services.UsuarioServices;
import com.ja.security.PasswordHash;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("usuarioservices")
    private UsuarioServices usuarioServices;

    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

    //UPDATE CONTRASEÑA
    @PutMapping(value= "/{email}/forgot")
    public ResponseEntity updatePasswordUsuario(@RequestBody UsuarioUpdatePasswordDTO usuarioUpdatePasswordDTO, @PathVariable(name="email") String email,
                                                   @RequestHeader(name="Authorization",required = false) String token) throws InvalidKeySpecException, NoSuchAlgorithmException {

        try{
            if(!decodeJWT(token).equals(email)){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        Usuario user = usuarioServices.findByEmail(email);
        if (user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(!usuarioServices.login(email, usuarioUpdatePasswordDTO.getOldPassword())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = new PasswordHash().createHash(usuarioUpdatePasswordDTO.getNewPassword());
        user.setPassword(hashedPassword);

        usuarioServices.altaUsuario(user);
        return new ResponseEntity(HttpStatus.OK);

    }
    //UPDATE CAMPOS
    @PutMapping(value= "/{email}")
    public ResponseEntity updateCamposUsuario(@RequestBody UsuarioUpdateCamposDTO usuarioUpdateCamposDTO, @PathVariable(name="email") String email,
                                              @RequestHeader(name="Authorization",required = false) String token) throws InvalidKeySpecException, NoSuchAlgorithmException {

        try{
            if(!decodeJWT(token).equals(email)){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        Usuario user = usuarioServices.findByEmail(email);
        if (user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.setUsername(usuarioUpdateCamposDTO.getUsername());
        user.setNombre(usuarioUpdateCamposDTO.getNombre());

        usuarioServices.altaUsuario(user);
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
        user.setPassword(hashedPassword(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setNombre(userDTO.getNombre());

        Usuario usuarioExsitente = usuarioServices.findByEmail(user.getEmail());
        if(usuarioExsitente==null) {
            if(usuarioServices.findByUsername(user.getUsername()) != null){
                return new ResponseEntity("username", HttpStatus.BAD_REQUEST);
            }
            usuarioServices.altaUsuario(user);
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
        if(usuarioServices.login(login.getEmail(),login.getPassword())){ // Llamada a gestorUsuarios
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
            boolean delete;
            delete = usuarioServices.deleteUsuarioByEmail(email);
            if(delete){
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping(value = "/{email}/image" )
    public ResponseEntity addImage(@PathVariable(name="email") String email, @RequestBody byte[] image,
                                   @RequestHeader(name="Authorization", required = false) String token){
        if(email == null || email.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!decodeJWT(token).equals(email)) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        Usuario user = usuarioServices.findByEmail(email);
        if (user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            user.setImage(image);

            usuarioServices.altaUsuario(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{email}/image" )
    public ResponseEntity getImage(@PathVariable(name="email") String email,
                                   @RequestHeader(name="Authorization", required = false) String token){

        try{
            decodeJWT(token);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Usuario user = usuarioServices.findByEmail(email);
        if (user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        byte[] image = user.getImage();
        return new ResponseEntity(image, HttpStatus.OK);
    }


    @GetMapping(value = "/{email}/firebase")
    public ResponseEntity getFirebaseToken(@PathVariable(name="email") String email,
                                           @RequestHeader(name="Authorization", required = false) String token){

        try{
            if(!token.equals("8jGerhqiOlLokORRMEx1WJqx0kCNqqXA")){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Usuario user = usuarioServices.findByEmail(email);
        if(user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user.getFirebaseToken(), HttpStatus.OK);
    }

    @PutMapping(value = "/{email}/firebase" )
    public ResponseEntity setFirebaseToken(@PathVariable(name="email") String email, @RequestBody String fToken,
                                   @RequestHeader(name="Authorization", required = false) String token){
        try {
            if (!decodeJWT(token).equals(email)) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Usuario user = usuarioServices.findByEmail(email);
        if (user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            user.setFirebaseToken(fToken);

            usuarioServices.altaUsuario(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }



}
