package api.dao;

import entities.Usuario;
import hibernate.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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