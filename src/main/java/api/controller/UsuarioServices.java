package api.controller;

import entities.Usuario;

import java.util.List;

public interface UsuarioServices {
    public boolean altaUsuario(Usuario usuario);
    public boolean deleteUsuarioByEmail(String username);
    public void updateUsuario(Usuario usuario);
    public List<Usuario> findAllUsuario();
    public Usuario findByUsername(String username);
    public Usuario findByEmail(String email);
}
