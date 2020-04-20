package api.controller;


import api.dto.MascotaIdDTO;
import api.dto.QuedadaDTO;
import api.dto.UsuarioDTO;
import api.services.MascotaServices;
import api.services.QuedadaServices;
import api.services.UsuarioServices;
import entities.Mascota;
import entities.MascotaId;
import entities.Quedada;

import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value="/api/quedadas")
public class QuedadaController {
    @Autowired
    private QuedadaServices quedadaServices;
    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private MascotaServices mascotaServices;

    // - Get todos los PerreParadas
    @GetMapping(value= "")
    public ResponseEntity getQuedadas(@RequestParam("ubicacion") String ubicacion, @RequestParam("admin") String admin,
                                          @RequestParam("participante") String participante, @RequestParam("order") String order) {

        List<Quedada> quedadas = new ArrayList<>();
        if(participante != null){
            Usuario user = usuarioServices.findByEmail(participante);
            if(user == null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            else{
                Set<Mascota> mascotas = user.getMascotas();
                for (Mascota masc : mascotas) quedadas.addAll(masc.getQuedadasPart());
            }
        }
        else if(admin != null){
            Usuario user = usuarioServices.findByEmail(admin);
            if(user == null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            else{
                quedadas = new ArrayList<>(user.getQuedadasAdmin());
            }
        }
        else {
           quedadas = quedadaServices.findAllQuedada();
        }
        if(order.equals("soonest")){

        }
        else if(order.equals("nearest")){

        }
        if(quedadas.isEmpty() ) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {

            return new ResponseEntity(quedadas,HttpStatus.OK);
        }
    }

    //READ USER
    @GetMapping(value= "/{id}")
    public ResponseEntity getPerreParadaById(@PathVariable(name="id") Integer id){
        Quedada quedada = quedadaServices.findById(id);
        if(quedada ==null ) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(quedada, HttpStatus.OK);
        }
    }

    //READ USER
    @PostMapping(value= "/{id}/participantes")
    public ResponseEntity addParticipante(@PathVariable(name="id") Integer id, @RequestBody MascotaIdDTO mascotaIdDTO){
        MascotaId mascotaId = new MascotaId(mascotaIdDTO.getNombre(), mascotaIdDTO.getAmo());
        Mascota mascota = mascotaServices.findById(mascotaId);
        Quedada quedada = quedadaServices.findById(id);
        if(mascota==null || quedada==null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        mascota.addQuedadaPart(quedada);
        mascotaServices.altaMascota(mascota);



        return new ResponseEntity(HttpStatus.CREATED);



    }

    @GetMapping(value="/{id}/participantes")
    public ResponseEntity getParticipantesQuedada(@PathVariable(name="id") Integer id){
        Quedada quedada = quedadaServices.findById(id);
        if(quedada==null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Set<Mascota> participantes = quedada.getParticipantes();
        if(participantes.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity(participantes,HttpStatus.OK);
        }
    }

    //CREATE USER
    @PostMapping(value= "")
    public ResponseEntity addPerreParada(@RequestBody QuedadaDTO quedadaDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Quedada quedada = new Quedada();
        ResponseEntity response;
        boolean exito;

        quedada.setAdmin(quedadaDTO.getAdmin());
        quedada.setCreatedAt(LocalDateTime.now());
        quedada.setFechaQuedada(quedadaDTO.getFechaQuedada());
        quedada.setLugarInicio(quedadaDTO.getLugarInicio());
        quedada.setLugarFin(quedadaDTO.getLugarFin());

        exito = quedadaServices.altaQuedada(quedada);

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
            boolean delete = quedadaServices.deleteQuedadaById(id);
            if(delete){
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}