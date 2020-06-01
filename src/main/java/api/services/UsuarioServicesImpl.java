package api.services;

import api.dao.UsuarioDAO;
import entities.Quedada;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import static io.github.ceraalex99.petandgo.GestorUsuarios.validatePassword;

@Service("usuarioservices")
@Transactional
public class UsuarioServicesImpl implements UsuarioServices {

    @Autowired
    @Qualifier("usuariorepository")
    private UsuarioDAO usuarioDAO;

    @Autowired
    @Qualifier("PerreParadaServices")
    private QuedadaServices quedadaServices;

    @Override
    public boolean altaUsuario(Usuario usuario) {
        return usuarioDAO.save(usuario) != null;
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
        preDelete(usuario.preRemove(),usuario);
        usuarioDAO.delete(usuario);
        return !usuarioDAO.findById(usuario.getEmail()).isPresent();
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public List<Usuario> findAllUsuario() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioDAO.findByUsername(username);
    }

    @Override
    public Usuario findByEmail(String email) {
        Usuario usuario = null;
        Optional<Usuario> user = usuarioDAO.findById(email);
        if (user.isPresent()) usuario = user.get();
        return  usuario;
    }

    @Override
    public boolean login(String email, String password) {
        try{
            boolean result = false;
            Usuario user = findByEmail(email);
            if (user != null) result = validatePassword(password,user.getPassword());
            return result;
        }catch (InvalidKeySpecException | NoSuchAlgorithmException e){
            return false;
        }
    }

    private void preDelete(Set <Quedada> quedadasAdmin,Usuario usuario) {
        if (quedadasAdmin != null) {
            Iterator<Quedada> itr = quedadasAdmin.iterator();
            Quedada q;
            while (itr.hasNext()) {
                q = itr.next();
                if (!q.getAdmin().equals(usuario.getEmail()))
                    quedadaServices.updateQuedada(q);
                else if (q.getParticipantes() == null || q.getParticipantes().isEmpty() )
                    quedadaServices.deleteQuedada(q);
            }
        }
        quedadaServices.flush();
    }

}
