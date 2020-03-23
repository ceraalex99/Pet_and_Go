package api.controller;

import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("UsuarioServices")
@Transactional

public class UsuarioServicesImpl implements UsuarioServices {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public void altaUsuario(Usuario usuario) {
        usuarioDAO.altaUsuario(usuario);
    }

    @Override
    public void deleteUsuarioByUsername(String username) {
        Usuario usuario = findByUsername(username);
        if(usuario != null) {
            usuarioDAO.deleteUsuarioByUsername(username);
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        usuarioDAO.updateUsuario(usuario);
    }

    @Override
    public List<Usuario> findAllUsuario() {
        return usuarioDAO.findAllUsuario();
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioDAO.findByUsername(username);
    }
}
