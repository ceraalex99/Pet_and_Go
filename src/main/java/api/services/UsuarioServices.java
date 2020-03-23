package api.services;

import entities.Usuario;

import java.util.List;

public interface UsuarioServices {
    public void altaUsuario(Usuario usuario);
    public void deleteUsuarioByUsername(String username);
    public void updateUsuario(Usuario usuario);
    public List<Usuario> findAllUsuario();
    public Usuario findByUsername(String username);
}
