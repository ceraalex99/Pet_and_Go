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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.github.ceraalex99.petandgo.GestorUsuarios.decodeJWT;


@RestController
@RequestMapping(value="/api/quedadas")
public class QuedadaController {

    @Autowired
    private QuedadaServices quedadaServices;

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private MascotaServices mascotaServices;

    // - READ QUEDADAS
    @GetMapping(value= "")
    public ResponseEntity getQuedadas(@RequestParam(value = "ubicacion", required = false) String ubicacion,
                                      @RequestParam(value = "admin", required = false) String admin,
                                      @RequestParam(value = "participante", required = false) String participante,
                                      @RequestParam(value = "order", required = false) String order) {

        Set<Quedada> quedadas = new HashSet<>(quedadaServices.findAllQuedada());
        if(ubicacion != null){}

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
                quedadas = user.getQuedadasAdmin();
            }
        }

        if(quedadas.isEmpty() ) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        if(order!=null){
            if(order.equals("time")){
                List<Quedada> quedadasOrdered = new ArrayList<>(quedadas);
                quedadasOrdered.sort(Comparator.comparing(Quedada::getFechaQuedada));
                quedadasOrdered.removeIf(q -> q.getFechaQuedada().compareTo(new Date()) < 0);
                return new ResponseEntity(quedadasOrdered, HttpStatus.OK);
            }
        }

        return new ResponseEntity(quedadas,HttpStatus.OK);

    }

    //READ QUEDADA
    @GetMapping(value= "/{id}")
    public ResponseEntity getQuedada(@PathVariable(name="id") Integer id){
        Quedada quedada = quedadaServices.findById(id);
        if(quedada ==null ) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(quedada, HttpStatus.OK);
        }
    }

    //CREATE PARTICIPANTE
    @PostMapping(value= "/{id}/participantes")
    public ResponseEntity addParticipante(@PathVariable(name="id") Integer id, @RequestBody MascotaIdDTO mascotaIdDTO,
                                          @RequestHeader(name="Authorization",required = false) String token){

        try{
            if(!decodeJWT(token).equals(mascotaIdDTO.getAmo())){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
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

    //READ PARTICIPANTES
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

    //DESAPUNTARSE QUEDADA
    @DeleteMapping(value="/{id}/participantes/{email}/mascotas/{nombre}")
    public ResponseEntity deleteParticipanteQuedada(@PathVariable(name="id") Integer id,
                                                    @PathVariable(name="email") String email,
                                                    @PathVariable(name="nombre") String nombre,
                                                    @RequestHeader(name="Authorization", required = false) String token){

        try{
            if(!decodeJWT(token).equals(email)){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Quedada quedada = quedadaServices.findById(id);
        if(quedada == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Mascota mascota = mascotaServices.findById(new MascotaId(nombre,email));
        if(mascota == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        mascota.removeQuedadaPart(quedada);
        mascotaServices.altaMascota(mascota);

        return new ResponseEntity(HttpStatus.OK);
    }

    //CREATE QUEDADA
    @PostMapping(value= "")
    public ResponseEntity addQuedada(@RequestBody QuedadaDTO quedadaDTO,
                                     @RequestHeader(name="Authorization",required = false) String token) {

        try{
            if(!decodeJWT(token).equals(quedadaDTO.getAdmin())){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Quedada quedada = new Quedada();
        int exito;

        quedada.setAdmin(quedadaDTO.getAdmin());
        quedada.setCreatedAt(new Date());
        quedada.setFechaQuedada(quedadaDTO.getFechaQuedada());
        quedada.setLugarInicio(quedadaDTO.getLugarInicio());
        quedada.setLugarFin(quedadaDTO.getLugarFin());

        exito = quedadaServices.altaQuedada(quedada);


        if (exito != -1) return new ResponseEntity(exito,HttpStatus.CREATED);
        else return new  ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);


    }

    //UPDATE QUEDADA
    @PutMapping(value = "/{id}")
    public ResponseEntity updatePerreParada(@RequestBody QuedadaDTO quedadaDTO , @PathVariable(name="id") Integer id,
                                                @RequestHeader(name="Authorization",required = false) String token) {

        try{
            if(!decodeJWT(token).equals(quedadaDTO.getAdmin())){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        TimeUnit timeunit = TimeUnit.HOURS;
        Date fechaactualmenos5h = new Date();
        long diffInHours = quedadaDTO.getFechaQuedada().getTime() - fechaactualmenos5h.getTime();
        //timeunit.convert(diffInHours,TimeUnit.HOURS);
        if(timeunit.toHours(diffInHours) < 5 ){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            Quedada quedada = quedadaServices.findById(id);
            if (quedada == null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            //quedada.setFechaQuedada(quedadaDTO.getFechaQuedada());
            quedada.setLugarInicio(quedadaDTO.getLugarInicio());
            quedada.setLugarFin(quedadaDTO.getLugarFin());
            quedadaServices.altaQuedada(quedada);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteQuedada(@PathVariable(name="id") Integer id,
                                        @RequestHeader(name="Authorization", required = false) String token){

        Quedada quedada = quedadaServices.findById(id);
        try{
            if(!decodeJWT(token).equals(quedada.getAdmin())){
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        if(quedada.getParticipantes() == null || quedada.getParticipantes().isEmpty()){
            boolean exito = quedadaServices.deleteQuedada(quedada);
            if(exito) return new ResponseEntity(HttpStatus.OK);
            else return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}