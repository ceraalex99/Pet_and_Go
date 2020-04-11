package api.dao;

import entities.Mascota;
import entities.Usuario;

import java.util.List;

public interface UsuarioDAO {
    boolean altaUsuario(Usuario usuario);
    boolean deleteUsuarioByEmail(String username);
    boolean deleteUsuario(Usuario usuario);
    void updateUsuario(Usuario usuario);
    List findAllUsuario();
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
}
