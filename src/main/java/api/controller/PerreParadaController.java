package api.controller;


import api.dto.PerreParadaDTO;
import api.services.MascotaServices;
import api.services.PerreParadaServices;
import api.services.UsuarioServices;
import entities.PerreParada;

import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(value="/api/perreParadas")
public class PerreParadaController {
    @Autowired
    private PerreParadaServices perreParadaServices;
    @Autowired
    private UsuarioServices usuarioServices;

    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";


    // - Get todos los PerreParadas
    @GetMapping(value= "")
    public ResponseEntity getPerreParadas( ) {
        List<PerreParada> perreParadas = perreParadaServices.findAllPerreParada();
        if(perreParadas==null ) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<List<PerreParada>>(perreParadas,HttpStatus.OK);
        }
    }

    //READ USER
    @GetMapping(value= "/{id}")
    public ResponseEntity getPerreParadaById(@PathVariable(name="id") Integer id){
        PerreParada perreParada= perreParadaServices.findById(id);
        if(perreParada==null ) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(perreParada, HttpStatus.OK);
        }
    }

    //READ USER
    @GetMapping(value= "/usuario/{email}")
    public ResponseEntity getPerreParadaById(@PathVariable(name="email") String email){
        Usuario usuario= usuarioServices.findByEmail(email);
        if(usuario==null ) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(usuario.getPerreParadas(), HttpStatus.OK);
        }
    }

    //CREATE USER
    @PostMapping(value= "")
    public ResponseEntity addPerreParada(@RequestBody PerreParadaDTO perreParadaDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {

        PerreParada perreParada = new PerreParada();
        ResponseEntity response;
        boolean exito;

        perreParada.setUsuarioAdmin(perreParadaDTO.getUsuarioAdmin());
        perreParada.setFechaCreacion(perreParadaDTO.getFechaCreacion());
        perreParada.setFechaQuedada(perreParadaDTO.getFechaQuedada());
        perreParada.setLugarInicio(perreParadaDTO.getLugarInicio());
        perreParada.setLugarFin(perreParadaDTO.getLugarFin());

        exito = perreParadaServices.altaPerreParada(perreParada);

        HttpHeaders httpHeaders = new HttpHeaders();

        if (exito) response = new ResponseEntity(httpHeaders,HttpStatus.CREATED);
        else response = new  ResponseEntity(httpHeaders,HttpStatus.CREATED);

        return response;

    }

    //DELETE USER
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePerreParada(@PathVariable(name="id") String sid){

        Integer id;

        try {
            id = Integer.parseInt(sid);
        } catch (NumberFormatException nfe) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(id == null || id == 0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else {
            boolean delete = perreParadaServices.deletePerreParadaById(id);
            if(delete){
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}