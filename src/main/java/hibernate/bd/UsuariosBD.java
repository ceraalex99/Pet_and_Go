package hibernate.bd;

import entities.Usuario;
import hibernate.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class UsuariosBD {

    private Session session;
    private static  SessionFactory sessionFactory = Factory.getSessionFactory(Usuario.class);

    private void newSession(){
        if (session == null) session = sessionFactory.openSession();
    }

    public Usuario add(Usuario user){
        newSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }

    public void update(Usuario user){
        newSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();

    }

    public Usuario get(String username){
        newSession();

        return session.get(Usuario.class, username);
    }

    public List<Usuario> getAll(){
        newSession();
        return session.createQuery("FROM Usuario").getResultList();
    }

    public boolean delete(Usuario user){
        boolean result = false;
        newSession();
        if(user != null) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            result = session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
        }
        return result;
    }

}
