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
    public boolean altaUsuario(Usuario usuario) {
        return usuarioDAO.altaUsuario(usuario);
    }

    @Override
    public boolean deleteUsuarioByUsername(String username) {
        return usuarioDAO.deleteUsuarioByUsername(username);
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
