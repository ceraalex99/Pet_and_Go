package api.services;

import entities.Usuario;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface UsuarioServices {
    boolean altaUsuario(Usuario usuario);
    boolean deleteUsuarioByEmail(String username);
    boolean deleteUsuario(Usuario usuario);
    void updateUsuario(Usuario usuario);
    List<Usuario> findAllUsuario();
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
    boolean login(String email,String password) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
