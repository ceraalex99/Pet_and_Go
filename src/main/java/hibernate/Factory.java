package hibernate;

import entities.Mascota;
import entities.Quedada;
import entities.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class Factory {

    private Factory(){
        throw new IllegalStateException("Utility class");
    }

    private static SessionFactory sessionFactoryUsuario;
    private static SessionFactory sessionFactoryMascota;
    private static SessionFactory sessionFactoryPerreParada;

    private static SessionFactory getSession(Class tipoEntity){
        File f = HibernateConfig.getConfigFile();
        return new Configuration().configure(f).addAnnotatedClass(tipoEntity).buildSessionFactory();
    }

    private static SessionFactory getSessionFactoryUsuario(){
        if (sessionFactoryUsuario == null)  sessionFactoryUsuario = new Configuration().configure(HibernateConfig.getConfigFile()).addAnnotatedClass(Usuario.class).addAnnotatedClass(Mascota.class).addAnnotatedClass(Quedada.class).buildSessionFactory();
        return sessionFactoryUsuario;
    }



    public static SessionFactory getSessionFactory(Class tipoClase){
        SessionFactory sessionFactory;

        if (Usuario.class.equals(tipoClase)) {
            sessionFactory = getSessionFactoryUsuario();
        } else if (Mascota.class.equals(tipoClase)) {
            sessionFactory = getSessionFactoryUsuario();
        } else if (Quedada.class.equals(tipoClase)) {
            sessionFactory = getSessionFactoryUsuario();
        }else{
            throw new IllegalStateException("Unexpected value: " + tipoClase.toString());
        }

        return sessionFactory;

    }

}
