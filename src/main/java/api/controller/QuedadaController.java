package api.controller;


import api.dto.MascotaIdDTO;
import api.dto.QuedadaDTO;
import api.services.MascotaServices;
import api.services.QuedadaServices;
import api.services.UsuarioServices;
import entities.Mascota;
import entities.MascotaId;
import entities.Quedada;

import entities.Usuario;
import helpers.CalculadoraDistancia;
import helpers.Posicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<List<Quedada>> getQuedadas(@RequestParam(value = "admin", required = false) String admin,
                                      @RequestParam(value = "participante", required = false) String participante,
                                      @RequestParam(value = "order", required = false) String order) {

        Set<Quedada> quedadas;

        if(participante != null){
            Usuario user = usuarioServices.findByEmail(participante);
            if(user == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                Set<Mascota> mascotas = user.getMascotas();
                quedadas = new  HashSet<>();
                for (Mascota masc : mascotas) quedadas.addAll(masc.getQuedadasPart());
            }
        }
        else if(admin != null){
            Usuario user = usuarioServices.findByEmail(admin);
            if(user == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                quedadas = user.getQuedadasAdmin();
            }
        }
        else{
            quedadas = new HashSet<>(quedadaServices.findAllQuedada());
        }

        if( quedadas == null || quedadas.isEmpty() ) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Quedada> quedadasOrdered = new ArrayList<>(quedadas);
        if(order!=null && order.equals("time")){
            quedadasOrdered.sort(Comparator.comparing(Quedada::getFechaQuedada));
            quedadasOrdered.removeIf(q -> q.getFechaQuedada().compareTo(new Date()) < 0);
        }

        return new ResponseEntity<>(quedadasOrdered,HttpStatus.OK);

    }

    //READ QUEDADA
    @GetMapping(value= "/{id}")
    public ResponseEntity<Quedada> getQuedada(@PathVariable(name="id") Integer id){
        Quedada quedada = quedadaServices.findById(id);
        if(quedada ==null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(quedada, HttpStatus.OK);
        }
    }

    //READ QUEDADA
    @GetMapping(value= "/distancia/{distanciaEnMetros}/{latitud}/{longitud}")
    public ResponseEntity<List<Quedada>> getQuedadasPorDistancia(@PathVariable(name="distanciaEnMetros") Integer distanciaEnMetros,
                                                  @PathVariable(name="latitud") double lat,
                                                  @PathVariable(name="longitud") double lon,
                                                  @RequestHeader(name="Authorization",required = false) String token){

        try {
            decodeJWT(token);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Posicion p1 = new Posicion(lat,lon);
        Posicion p2;
        int distancia;

        List<Quedada> quedadas = new ArrayList<>();
        for (Quedada q: quedadaServices.findAllQuedada()){
            if (q.getFechaQuedada().compareTo(new Date()) >= 0){
                p2 = new Posicion(q);
                distancia = CalculadoraDistancia.getDistanciaMetros(p1,p2);
                if (distancia <= distanciaEnMetros) quedadas.add(q);
            }
        }

        quedadas.sort(Comparator.comparing(Quedada::getFechaQuedada));

        return new ResponseEntity<>(quedadas, HttpStatus.OK);

    }

    //CREATE PARTICIPANTE
    @PostMapping(value= "/{id}/participantes")
    public ResponseEntity<Void> addParticipante(@PathVariable(name="id") Integer id, @RequestBody MascotaIdDTO mascotaIdDTO,
                                          @RequestHeader(name="Authorization",required = false) String token){

        try{
            if(!decodeJWT(token).equals(mascotaIdDTO.getAmo())){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        MascotaId mascotaId = new MascotaId(mascotaIdDTO.getNombre(), mascotaIdDTO.getAmo());
        Mascota mascota = mascotaServices.findById(mascotaId);
        Quedada quedada = quedadaServices.findById(id);
        if(mascota==null || quedada==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        mascota.addQuedadaPart(quedada);
        mascotaServices.altaMascota(mascota);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //READ PARTICIPANTES
    @GetMapping(value="/{id}/participantes")
    public ResponseEntity<Set<Mascota>> getParticipantesQuedada(@PathVariable(name="id") Integer id){
        Quedada quedada = quedadaServices.findById(id);
        if(quedada==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Set<Mascota> participantes = quedada.getParticipantes();
        if(participantes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(participantes,HttpStatus.OK);
        }
    }

    //DESAPUNTARSE QUEDADA
    @DeleteMapping(value="/{id}/participantes/{email}/mascotas/{nombre}")
    public ResponseEntity<Void> deleteParticipanteQuedada(@PathVariable(name="id") Integer id,
                                                    @PathVariable(name="email") String email,
                                                    @PathVariable(name="nombre") String nombre,
                                                    @RequestHeader(name="Authorization", required = false) String token){

        try{
            if(!decodeJWT(token).equals(email)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Quedada quedada = quedadaServices.findById(id);
        if(quedada == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Mascota mascota = mascotaServices.findById(new MascotaId(nombre,email));
        if(mascota == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        mascota.removeQuedadaPart(quedada);
        mascotaServices.updateMascota(mascota);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //CREATE QUEDADA
    @PostMapping(value= "")
    public ResponseEntity<Integer> addQuedada(@RequestBody QuedadaDTO quedadaDTO,
                                     @RequestHeader(name="Authorization",required = false) String token) {

        try{
            if(!decodeJWT(token).equals(quedadaDTO.getAdmin())){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Quedada quedada = new Quedada();
        int exito;

        quedada.setAdmin(quedadaDTO.getAdmin());
        quedada.setCreatedAt(new Date());
        quedada.setFechaQuedada(quedadaDTO.getFechaQuedada());
        quedada.setLugarInicio(quedadaDTO.getLugarInicio());
        quedada.setLatitud(quedadaDTO.getLatitud());
        quedada.setLongitud(quedadaDTO.getLongitud());
        quedada.setIdImageGoogle(quedadaDTO.getIdImageGoogle());

        exito = quedadaServices.altaQuedada(quedada);


        if (exito != -1) return new ResponseEntity<>(quedada.getId(),HttpStatus.CREATED);
        else return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


    }

    //UPDATE QUEDADA
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updatePerreParada(@RequestBody QuedadaDTO quedadaDTO , @PathVariable(name="id") Integer id,
                                                @RequestHeader(name="Authorization",required = false) String token) {

        try{
            if(!decodeJWT(token).equals(quedadaDTO.getAdmin())){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        TimeUnit timeunit = TimeUnit.HOURS;
        Date fechaactualmenos5h = new Date();
        long diffInHours = quedadaDTO.getFechaQuedada().getTime() - fechaactualmenos5h.getTime();
        if(timeunit.toHours(diffInHours) < 5 ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            Quedada quedada = quedadaServices.findById(id);
            if (quedada == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            quedada.setLugarInicio(quedadaDTO.getLugarInicio());
            quedada.setLatitud(quedadaDTO.getLatitud());
            quedada.setLongitud(quedadaDTO.getLongitud());
            quedada.setIdImageGoogle(quedadaDTO.getIdImageGoogle());
            quedada.setFechaQuedada(quedadaDTO.getFechaQuedada());

            quedadaServices.updateQuedada(quedada);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteQuedada(@PathVariable(name="id") Integer id,
                                        @RequestHeader(name="Authorization", required = false) String token){

        Quedada quedada = quedadaServices.findById(id);
        try{
            if(!decodeJWT(token).equals(quedada.getAdmin())){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(quedada.getParticipantes() == null || quedada.getParticipantes().isEmpty()){
            boolean exito = quedadaServices.deleteQuedada(quedada);
            if(exito) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}