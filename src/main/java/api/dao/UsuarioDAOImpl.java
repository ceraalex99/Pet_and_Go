package api.dao;

import entities.Usuario;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@Transactional

public class UsuarioDAOImpl extends AbstractSession implements UsuarioDAO {

    @Override
    public void altaUsuario(Usuario usuario) {
        getSession().persist(usuario);
    }

    @Override
    public void deleteUsuarioByUsername(String username) {
        Usuario usuario = findByUsername(username);
        if(usuario != null) {
            getSession().delete(usuario);
        }
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
}