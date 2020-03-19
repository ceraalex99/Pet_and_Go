package api.dao;

import Entities.Usuario;

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
    public void deleteUsuarioByUsername(String Username) {
        Usuario usuario = findByUsername(Username);
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
    public Usuario findByUsername(String Username) {
        return (Usuario) getSession().get(Usuario.class, Username);
    }
}