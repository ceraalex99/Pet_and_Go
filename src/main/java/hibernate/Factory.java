package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class Factory {

    private Factory(){
        throw new IllegalStateException("Utility class");
    }

    public static SessionFactory getSession(Class tipoEntity){
        File f = HibernateConfig.getConfigFile();
        return new Configuration().configure(f).addAnnotatedClass(tipoEntity).buildSessionFactory();
    }

}
