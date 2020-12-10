package application.services;

import infrastructure.dao.MascotaDAO;
import domain.models.Mascota;
import domain.models.MascotaId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("mascotaServices")
@Transactional
public class MascotaServicesImpl implements MascotaServices {

    @Autowired
    MascotaDAO mascotaDAO;

    @Override
    public boolean altaMascota(Mascota mascota) {
        return mascotaDAO.save(mascota) != null;
    }

    @Override
    public boolean deleteMascotaById(MascotaId id) {
        mascotaDAO.deleteById(id);
        return !mascotaDAO.findById(id).isPresent();
    }

    @Override
    public void updateMascota(Mascota mascota) {
        mascotaDAO.save(mascota);
    }

    @Override
    public Mascota findById(MascotaId id) {
        Mascota mascota = null;
        Optional<Mascota> resultMascota = mascotaDAO.findById(id);
        if (resultMascota.isPresent()) mascota = resultMascota.get();
        return  mascota;
    }

}
