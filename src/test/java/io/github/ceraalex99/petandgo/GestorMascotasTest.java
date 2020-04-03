package io.github.ceraalex99.petandgo;

import api.dto.MascotaDTO;
import entities.KeysComposites.MascotaPK;
import entities.Mascota;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class GestorMascotasTest {

    @Test
    public void addTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String nombre = "Rocky";
        String emailUsuario = "antoniogp68@gmail.com";
        //Date fechaNacimiento = new Date(System.currentTimeMillis());
        String fechaNacimiento = "fecha";
        MascotaDTO mascotaDTO = new MascotaDTO(nombre,emailUsuario,fechaNacimiento);
        GestorMascotas.add(mascotaDTO);

        Mascota mascota = new Mascota();
/*
        List<Mascota> mascotas = GestorMascotas.getAll();
        for (Mascota m : mascotas) {
            if (m.getKey().getEmailUsuario().equals(emailUsuario) && m.getKey().getNombre().equals(nombre)) {
                mascota = m;
            }
        }
*/

        mascota = GestorMascotas.get(nombre,emailUsuario);
        assertEquals(nombre,mascota.getKey().getNombre());
        assertEquals(emailUsuario,mascota.getKey().getEmailUsuario());
        assertEquals(fechaNacimiento, mascota.getFechaNacimiento());
        GestorMascotas.delete(mascota);

    }

}
