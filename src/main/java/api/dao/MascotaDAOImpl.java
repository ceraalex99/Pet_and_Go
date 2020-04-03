package api.dao;

import entities.KeysComposites.MascotaPK;
import entities.Mascota;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public class MascotaDAOImpl extends  AbstractSession implements MascotaDAO,SessionBD {

    @Override
    public boolean altaMascota(Mascota mascota) {
        getSession().beginTransaction();
        getSession().save(mascota);
        getSession().getTransaction().commit();
        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean deleteMascota(String username,String emailUsuario) {
        boolean result = false;
        Mascota mascota = find(username,emailUsuario);
        if(mascota != null) result = deleteMascota(mascota);
        return result;
    }

    @Override
    public boolean deleteMascota(Mascota mascota) {
        boolean result = false;

        getSession().beginTransaction();
        getSession().delete(mascota);
        getSession().getTransaction().commit();
        result = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;

        return result;
    }

    @Override
    public void updateMascota(Mascota mascota) {
        getSession().update(mascota);
    }

    @Override
    public List<Mascota> findAllMascota() {
        return getSession().createQuery("from Mascota").list();
    }

    @Override
    public Mascota find(String nombre,String emailUsuario) {
        return getSession().get(Mascota.class, new MascotaPK(nombre,emailUsuario));
    }

    @Override
    public Session getSession() {
        return getSession(Mascota.class);
    }
}