package api.controller;

import entities.Usuario;
import hibernate.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractSession {

    private Session session;
    private static  SessionFactory sessionFactory = Factory.getSession(Usuario.class);

    private void newSession(){
        if (session == null) session = sessionFactory.openSession();
    }

    protected Session getSession() {
        newSession();
        return session;
    }
}