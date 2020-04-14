package api.dao;

import entities.Usuario;
import hibernate.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface SessionBD {

    Session getSession();
    
}