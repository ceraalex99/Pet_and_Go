package io.github.ceraalex99.petandgo;

import api.dao.MascotaDAO;
import api.dao.MascotaDAOImpl;
import api.dto.MascotaDTO;
import com.ja.security.PasswordHash;
import entities.Mascota;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

public class GestorMascotas {
    private GestorMascotas(){
        throw new IllegalStateException("Utility class");
    }

    private static MascotaDAO mascotasBD = new MascotaDAOImpl();

    public static void add(MascotaDTO mascotaDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Mascota mascota = new Mascota(mascotaDTO.getNombre(), mascotaDTO.getFechaNacimiento(),mascotaDTO.getEmailUsuario());
        mascotasBD.altaMascota(mascota);
    }

    public static void delete(Mascota mascota) {
        mascotasBD.deleteMascota(mascota);
    }

    public static Mascota get(String nombre,String emailUsuario) {
        return mascotasBD.find(nombre,emailUsuario);
    }

    public static List<Mascota> getAll() {
        return mascotasBD.findAllMascota();
    }

}