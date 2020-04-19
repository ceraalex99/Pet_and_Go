package api.dao;


import hibernate.Factory;
import org.hibernate.Session;


public class AbstractSession {

    private Session session;

    private void newSession(Class tipoClase){
        if (session == null) session = Factory.getSessionFactory(tipoClase).openSession();
    }

    protected Session getSession(Class tipoClase) {
        newSession(tipoClase);
        return session;
    }

}