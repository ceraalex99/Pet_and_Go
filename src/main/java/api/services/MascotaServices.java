package api.services;

import entities.Mascota;
import entities.MascotaId;

import java.util.List;

public interface MascotaServices {
    boolean altaMascota(Mascota mascota);
    boolean deleteMascotaById(MascotaId id);
    void deleteMascota(Mascota mascota);
    void updateMascota(Mascota mascota);
    List<Mascota> findAllMascota();
    Mascota findById(MascotaId id);
}