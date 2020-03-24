package io.github.ceraalex99.petandgo;

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
        UsuariosBD usuariosBD = new UsuariosBD();
        usuariosBD.add(user);
    }

    public static boolean login(String id, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Usuario user = new UsuariosBD().get(id);
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