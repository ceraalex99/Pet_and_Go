package api.dao;


import hibernate.Factory;
import org.hibernate.Session;


public class AbstractSession {

    private static AbstractSession instance;

    private Session session;

    private void newSession(Class tipoClase){
        if (session == null) session = Factory.getSessionFactory(tipoClase).openSession();
    }

    public Session getSession(Class tipoClase) {
        newSession(tipoClase);
        return session;
    }

    private AbstractSession(){
    }

    public static AbstractSession getAbstractSession(){
        if(instance==null){
            instance = new AbstractSession();
        }
        return instance;
    }

}