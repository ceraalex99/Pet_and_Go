package api.dao;

import entities.Mascota;
import entities.MascotaId;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MascotaDAOImpl implements MascotaDAO  {

    private Session session = AbstractSession.getAbstractSession().getSession(Mascota.class);

    @Override
    public boolean altaMascota(Mascota mascota) {
        session.beginTransaction();
            session.save(mascota);
        session.getTransaction().commit();
        boolean result = session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
        session.clear();
        return result;
    }

    @Override
    public boolean deleteMascotaById(MascotaId id) {
        boolean result = false;
        Mascota mascota = findById(id);
        if(mascota != null) {
            session.beginTransaction();
            session.delete(mascota);
            session.getTransaction().commit();
            result = session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
            session.clear();
        }
        return result;
    }

    @Override
    public void updateMascota(Mascota mascota) {
        session.update(mascota);
    }

    @Override
    public List<Mascota> findAllMascota() {
        return session.createQuery("from Mascota").list();
    }

    @Override
    public Mascota findById(MascotaId id) {
        return session.get(Mascota.class, id);
    }



}
