package api.services;

import Entities.Usuario;

import java.util.List;

public interface UsuarioServices {
    public void altaUsuario(Usuario usuario);
    public void deleteUsuarioByUsername(String Username);
    public void updateUsuario(Usuario usuario);
    public List<Usuario> findAllUsuario();
    public Usuario findByUsername(String Username);
}
