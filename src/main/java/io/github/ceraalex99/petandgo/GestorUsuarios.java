package io.github.ceraalex99.petandgo;

import com.ja.security.PasswordHash;
import Entities.Usuario;
import hibernate.BD.UsuariosBD;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class GestorUsuarios {
    public void signUp(String nombre, String username, String password, String email) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String hashedPassword = new PasswordHash().createHash(password);
        Usuario user = new Usuario(nombre, username, hashedPassword, email);
        UsuariosBD usuariosBD = new UsuariosBD();
        usuariosBD.add(user);
    }
}
