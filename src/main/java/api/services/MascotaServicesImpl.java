package api.services;

import api.dao.MascotaDAO;
import entities.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("MascotaServices")
@Transactional

public class MascotaServicesImpl implements MascotaServices {
    @Autowired
    private MascotaDAO mascotaDAO;

    @Override
    public boolean altaMascota(Mascota mascota) {
        return mascotaDAO.altaMascota(mascota);
    }

    @Override
    public boolean deleteMascota(String name,String emailUsuario) {
        return mascotaDAO.deleteMascota(name,emailUsuario);
    }

    @Override
    public boolean deleteMascota(Mascota mascota) {
        return mascotaDAO.deleteMascota(mascota);
    }

    @Override
    public void updateMascota(Mascota mascota) {
        mascotaDAO.updateMascota(mascota);
    }

    @Override
    public List<Mascota> findAllMascota() {
        return mascotaDAO.findAllMascota();
    }

    @Override
    public Mascota find(String nombre,String emailUsuario) {
        return mascotaDAO.find(nombre,emailUsuario);
    }
}
