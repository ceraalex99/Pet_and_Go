package api.services;

import entities.Usuario;

import java.util.List;

public interface UsuarioServices {
    boolean altaUsuario(Usuario usuario);
    boolean deleteUsuarioByEmail(String username);
    void updateUsuario(Usuario usuario);
    List<Usuario> findAllUsuario();
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
}
