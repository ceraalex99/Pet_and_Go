package api.services;

import api.dao.MascotaDAO;
import entities.Mascota;
import entities.MascotaId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("mascotaServices")
@Transactional
public class MascotaServicesImpl implements MascotaServices {
    @Autowired
    MascotaDAO mascotaDAO;

    @Override
    public boolean altaMascota(Mascota mascota) {
        return mascotaDAO.altaMascota(mascota);
    }

    @Override
    public boolean deleteMascotaById(MascotaId id) {
        return mascotaDAO.deleteMascotaById(id);
    }

    @Override
    public void updateMascota(Mascota mascota) {
        mascotaDAO.updateMascota(mascota);
    }

    @Override
    public List findAllMascota() {
        return mascotaDAO.findAllMascota();
    }

    @Override
    public Mascota findById(MascotaId id) {
        return mascotaDAO.findById(id);
    }
}
