package hibernate;

import entities.Mascota;
import entities.PerreParada;
import entities.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.List;

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
        if (sessionFactoryUsuario == null)  sessionFactoryUsuario = new Configuration().configure(HibernateConfig.getConfigFile()).addAnnotatedClass(Usuario.class).addAnnotatedClass(Mascota.class).addAnnotatedClass(PerreParada.class).buildSessionFactory();
        return sessionFactoryUsuario;
    }

    private static SessionFactory getSessionFactoryMascota(){
        if (sessionFactoryMascota == null)  sessionFactoryMascota = new Configuration().configure(HibernateConfig.getConfigFile()).addAnnotatedClass(Usuario.class).addAnnotatedClass(Mascota.class).buildSessionFactory();
        return sessionFactoryMascota;
    }

    private static SessionFactory getSessionFactoryPerreParada(){
        if (sessionFactoryPerreParada == null)  sessionFactoryPerreParada = new Configuration().configure(HibernateConfig.getConfigFile()).addAnnotatedClass(PerreParada.class).buildSessionFactory();
        return sessionFactoryPerreParada;
    }

    public static SessionFactory getSessionFactory(Class tipoClase){
        SessionFactory sessionFactory;

        if (Usuario.class.equals(tipoClase)) {
            sessionFactory = getSessionFactoryUsuario();
        } else if (Mascota.class.equals(tipoClase)) {
            sessionFactory = getSessionFactoryMascota();
        } else if (PerreParada.class.equals(tipoClase)) {
            sessionFactory = getSessionFactoryPerreParada();
        }else{
            throw new IllegalStateException("Unexpected value: " + tipoClase.toString());
        }

        return sessionFactory;

    }

}
