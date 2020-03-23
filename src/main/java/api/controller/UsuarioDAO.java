package api.controller;

import entities.Usuario;

import java.util.List;

public interface UsuarioDAO {
    public void altaUsuario(Usuario usuario);
    public void deleteUsuarioByUsername(String username);
    public void updateUsuario(Usuario usuario);
    public List<Usuario> findAllUsuario();
    public Usuario findByUsername(String username);
}
