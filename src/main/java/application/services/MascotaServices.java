package application.services;

import domain.models.Mascota;
import domain.models.MascotaId;

public interface MascotaServices {
    boolean altaMascota(Mascota mascota);

    boolean deleteMascotaById(MascotaId id);

    void updateMascota(Mascota mascota);

    Mascota findById(MascotaId id);
}