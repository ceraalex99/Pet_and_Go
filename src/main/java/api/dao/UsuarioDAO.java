package api.dao;

import entities.Usuario;

import java.util.List;

public interface UsuarioDAO {
    boolean altaUsuario(Usuario usuario);
    boolean deleteUsuarioByEmail(String username);
    void updateUsuario(Usuario usuario);
    List findAllUsuario();
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
}
