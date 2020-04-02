package hibernate.bd;

import entities.Mascota;
import hibernate.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class MascotasBD {

    private Session session;
    private static  SessionFactory sessionFactory = Factory.getSession(Mascota.class);

    private void newSession(){
        if (session == null) session = sessionFactory.openSession();
    }

    public Mascota add(Mascota mascota){
        newSession();
        session.beginTransaction();
        session.save(mascota);
        session.getTransaction().commit();
        return mascota;
    }

    public void update(Mascota mascota){
        newSession();
        session.beginTransaction();
        session.update(mascota);
        session.getTransaction().commit();

    }
/*
    public Mascota get(String username){
        newSession();

        return session.get(Mascota.class, username);
    }
*/
    public List<Mascota> getAll(){
        newSession();
        return session.createQuery("FROM Mascota").getResultList();
    }

    public boolean delete(Mascota mascota){
        boolean result = false;
        newSession();
        if(mascota != null) {
            session.beginTransaction();
            session.delete(mascota);
            session.getTransaction().commit();
            result = session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
        }
        return result;
    }

}
