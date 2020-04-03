package api.services;

import entities.Mascota;

import java.util.List;


public interface MascotaServices {
    boolean altaMascota(Mascota Mascota);
    boolean deleteMascota(String name,String emailUsername);
    boolean deleteMascota(Mascota Mascota);
    void updateMascota(Mascota Mascota);
    List<Mascota> findAllMascota();
    Mascota find(String nombre,String emailUsuario);
}
