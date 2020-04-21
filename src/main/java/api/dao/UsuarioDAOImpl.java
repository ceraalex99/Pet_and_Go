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

public class UsuarioDAOImpl implements UsuarioDAO {

    private Session session = AbstractSession.getAbstractSession().getSession(Mascota.class);

    @Override
    public boolean altaUsuario(Usuario usuario) {
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.getTransaction();
        return session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean deleteUsuarioByEmail(String email) {
        boolean result = false;
        Usuario usuario = findByEmail(email);
        if(usuario != null) {
            result = deleteUsuario(usuario);
        }
        return result;
    }

    @Override
    public boolean deleteUsuario(Usuario usuario) {
        boolean result = false;

        session.beginTransaction();
        session.delete(usuario);
        session.getTransaction().commit();
        result = session.getTransaction().getStatus() == TransactionStatus.COMMITTED;

        return result;
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        session.update(usuario);
    }

    @Override
    public List<Usuario> findAllUsuario() {
        return session.createQuery("from Usuario").list();
    }

    @Override
    public Usuario findByUsername(String username) {
        return session.get(Usuario.class, username);
    }

    @Override
    public Usuario findByEmail(String email) {
        return session.get(Usuario.class, email);
    }

}