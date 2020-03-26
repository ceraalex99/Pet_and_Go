package api.controller;

import entities.Usuario;

import java.util.List;

public interface UsuarioDAO {
    public boolean altaUsuario(Usuario usuario);
    public boolean deleteUsuarioByEmail(String username);
    public void updateUsuario(Usuario usuario);
    public List findAllUsuario();
    public Usuario findByUsername(String username);
    public Usuario findByEmail(String email);
}
