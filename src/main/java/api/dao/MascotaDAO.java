package api.dao;

import entities.Mascota;

import java.util.List;

public interface MascotaDAO {
    boolean altaMascota(Mascota mascota);
    boolean deleteMascota(String nombre,String emailUsuario);
    boolean deleteMascota(Mascota mascota);
    void updateMascota(Mascota mascota);
    List findAllMascota();
    Mascota find(String nombre,String emailUsuario);
}
