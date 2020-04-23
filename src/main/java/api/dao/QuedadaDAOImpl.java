package api.dao;

import entities.Mascota;
import entities.Quedada;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public class QuedadaDAOImpl implements QuedadaDAO {

    private Session session = AbstractSession.getAbstractSession().getSession(Quedada.class);

    @Override
    public boolean altaQuedada(Quedada quedada) {
        session.beginTransaction();
        session.save(quedada);
        session.getTransaction().commit();
        boolean result = session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
        session.clear();
        return result;
    }

    @Override
    public boolean deleteQuedadaById(Integer id) {
        boolean result = false;
        Quedada quedada = findById(id);
        if(quedada != null) {
            result = deleteQuedada(quedada);
        }
        return result;
    }

    @Override
    public boolean deleteQuedada(Quedada quedada) {
        boolean result = false;

        session.beginTransaction();
        session.delete(quedada);
        session.getTransaction().commit();
        result = session.getTransaction().getStatus() == TransactionStatus.COMMITTED;

        return result;
    }

    @Override
    public void updateQuedada(Quedada quedada) {
        session.update(quedada);
    }

    @Override
    public List<Quedada> findAllQuedada() {
        return session.createQuery("from Quedada").list();
    }

    @Override
    public Quedada findById(Integer id) {
        return session.get(Quedada.class, id);
    }
    
}