package api.dao;

import entities.PerreParada;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public class PerreParadaDAOImpl extends AbstractSession implements PerreParadaDAO,SessionBD {

    @Override
    public boolean altaPerreParada(PerreParada perreParada) {
        getSession().beginTransaction();
        try {
            getSession().save(perreParada);
            getSession().getTransaction().commit();
            getSession().getTransaction();
        } catch (Exception ex) {
            getSession().getTransaction().rollback();

        }

        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean deletePerreParadaById(Integer id) {
        boolean result = false;
        PerreParada perreParada = findById(id);
        if(perreParada != null) {
            result = deletePerreParada(perreParada);
        }
        return result;
    }

    @Override
    public boolean deletePerreParada(PerreParada perreParada) {
        boolean result = false;

        getSession().beginTransaction();
        getSession().delete(perreParada);
        getSession().getTransaction().commit();
        result = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;

        return result;
    }

    @Override
    public void updatePerreParada(PerreParada perreParada) {
        getSession().update(perreParada);
    }

    @Override
    public List<PerreParada> findAllPerreParada() {
        return getSession().createQuery("from PerreParada").list();
    }

    @Override
    public PerreParada findById(Integer id) {
        return getSession().get(PerreParada.class, id);
    }

    @Override
    public Session getSession() {
        return getSession(PerreParada.class);
    }
}