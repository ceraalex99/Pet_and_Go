package io.github.ceraalex99.petandgo;

import api.controller.UsuarioDAO;
import api.controller.UsuarioDAOImpl;
import com.ja.security.PasswordHash;
import entities.Usuario;
import hibernate.bd.UsuariosBD;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class GestorUsuarios {
    private GestorUsuarios(){
        throw new IllegalStateException("Utility class");
    }

    public static void signUp(String nombre, String username, String password, String email) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String hashedPassword = new PasswordHash().createHash(password);
        Usuario user = new Usuario(nombre, username, hashedPassword, email);
        UsuarioDAO usuariosBD = new UsuarioDAOImpl();
        usuariosBD.altaUsuario(user);
    }

    public static boolean login(String id, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Usuario user = new UsuarioDAOImpl().findByEmail(id); // ID = EMAIL
        try {
            return new PasswordHash().validatePassword(password, user.getPassword());
        }
        catch (NullPointerException e){
            return false;
        }

    }

    public static void delete(Usuario user) {
        UsuariosBD usuariosBD = new UsuariosBD();
        usuariosBD.delete(user);
    }
}