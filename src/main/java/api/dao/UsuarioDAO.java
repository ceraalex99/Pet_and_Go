package api.dao;

import Entities.Usuario;

import java.util.List;

public interface UsuarioDAO {
    public void altaUsuario(Usuario usuario);
    public void deleteUsuarioByUsername(String Username);
    public void updateUsuario(Usuario usuario);
    public List<Usuario> findAllUsuario();
    public Usuario findByUsername(String Username);
}
