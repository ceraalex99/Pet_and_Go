package api.dao;

import entities.Mascota;
import entities.Usuario;

import javax.transaction.Transactional;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

@Repository
@Transactional

public class UsuarioDAOImpl extends AbstractSession implements UsuarioDAO,SessionBD {

    @Override
    public boolean altaUsuario(Usuario usuario) {
        getSession().beginTransaction();
        getSession().save(usuario);
        getSession().getTransaction().commit();
        getSession().getTransaction();
        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean deleteUsuarioByEmail(String username) {
        boolean result = false;
        Usuario usuario = findByUsername(username);
        if(usuario != null) {
            result = deleteUsuario(usuario);
        }
        return result;
    }

    @Override
    public boolean deleteUsuario(Usuario usuario) {
        boolean result = false;

        getSession().beginTransaction();
        getSession().delete(usuario);
        getSession().getTransaction().commit();
        result = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;

        return result;
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        getSession().update(usuario);
    }

    @Override
    public List<Usuario> findAllUsuario() {
        return getSession().createQuery("from Usuario").list();
    }

    @Override
    public Usuario findByUsername(String username) {
        return getSession().get(Usuario.class, username);
    }

    @Override
    public Usuario findByEmail(String email) {
        return getSession().get(Usuario.class, email);
    }

    @Override
    public Session getSession() {
        return getSession(Usuario.class);
    }
}