package api.dao;

import entities.Mascota;
import entities.MascotaId;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class MascotaDAOImpl extends AbstractSession implements MascotaDAO  {
    @Override
    public boolean altaMascota(Mascota mascota) {
        getSession().beginTransaction();
        getSession().save(mascota);
        getSession().getTransaction().commit();
        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean deleteMascotaById(MascotaId id) {
        boolean result = false;
        Mascota mascota = findById(id);
        if(mascota != null) {
            getSession().beginTransaction();
            getSession().delete(mascota);
            getSession().getTransaction().commit();
            result = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
        }
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
    public Mascota findById(MascotaId id) {
        return getSession().get(Mascota.class, id);
    }


}
