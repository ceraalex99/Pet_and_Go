package api.dao;

import entities.Mascota;
import entities.MascotaId;
import entities.Usuario;

import java.util.List;

public interface MascotaDAO {
    boolean altaMascota(Mascota mascota);
    boolean deleteMascotaById(MascotaId id);
    void updateMascota(Mascota mascota);
    List findAllMascota();
    Mascota findById(MascotaId id);
}
