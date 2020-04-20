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

public class QuedadaDAOImpl extends AbstractSession implements QuedadaDAO,SessionBD {

    @Override
    public boolean altaQuedada(Quedada quedada) {
        getSession().beginTransaction();
        try {
            getSession().save(quedada);
            getSession().getTransaction().commit();
            getSession().getTransaction();
        } catch (Exception ex) {
            getSession().getTransaction().rollback();

        }

        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
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

        getSession().beginTransaction();
        getSession().delete(quedada);
        getSession().getTransaction().commit();
        result = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;

        return result;
    }

    @Override
    public void updateQuedada(Quedada quedada) {
        getSession().update(quedada);
    }

    @Override
    public List<Quedada> findAllQuedada() {
        return getSession().createQuery("from Quedada").list();
    }

    @Override
    public Quedada findById(Integer id) {
        return getSession().get(Quedada.class, id);
    }


    @Override
    public Session getSession() {
        return getSession(Quedada.class);
    }
}