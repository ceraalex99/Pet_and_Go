package api.controller;

import api.dto.MascotaDTO;
import api.services.MascotaServices;
import entities.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/*
import static io.github.ceraalex99.petandgo.GestorMascotas.login;
*/
import static io.github.ceraalex99.petandgo.GestorMascotas.add;

@RestController
@RequestMapping(value="/api/mascotas")
public class MascotaController {
    @Autowired
    private MascotaServices mascotaServices;

    // - Get todos los Mascotas
    @GetMapping(value= "")
    public ResponseEntity getMascotas( ) {
        List<Mascota> mascotas = mascotaServices.findAllMascota();
        if(mascotas==null ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<List<Mascota>>(mascotas,HttpStatus.OK);
        }
    }

  /*  @PostMapping(value= "")
    public ResponseEntity addMascota(@RequestBody MascotaDTO mascotaDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Mascota mascota = new Mascota();

        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setFechaNacimiento(mascotaDTO.getFechaNacimiento());
        mascota.setEmailUsuario(mascotaDTO.getEmailUsuario());
        mascota.setNombre(mascotaDTO.getNombre());

        Mascota mascotaExsitente = mascotaServices.find(mascota.getNombre(),mascota.getEmailUsuario());

        if(mascotaExsitente==null) {
            add(mascotaDTO); //Llamada a gestorMascotas
            return new ResponseEntity("Mascota creada con éxito", HttpStatus.OK);
        }
        return new ResponseEntity("La mascota indicada ya existe", HttpStatus.BAD_REQUEST);
    }
*/
}