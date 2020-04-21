package io.github.ceraalex99.petandgo;

import entities.Usuario;
import com.ja.security.PasswordHash;
import hibernate.bd.UsuariosBD;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static org.junit.Assert.*;

public class GestorUsuariosTest {

    @Test
    public void signUpTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String nombre = RandomStringUtils.random(25, true, false);
        String username = RandomStringUtils.random(10, true, true);
        String email = RandomStringUtils.random(30, true, true);
        String password = RandomStringUtils.random(10, true, true);
        GestorUsuarios.signUp(nombre,username,password,email);


        Usuario user = new Usuario();
        UsuariosBD usuariosBD = new UsuariosBD();
        List<Usuario> users = usuariosBD.getAll();
        for (Usuario usuario : users) {
            if (usuario.getUsername().equals(username)) {
                user = usuario;
            }
        }

        assertEquals(nombre, user.getNombre());
        assertEquals(username, user.getUsername());
        assertEquals(email,user.getEmail());
        assertTrue(new PasswordHash().validatePassword(password, user.getPassword()));
        GestorUsuarios.deleteByEmail(email);
    }

    @Test
    public void correctLoginTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        assertTrue(GestorUsuarios.login("pepepepote@pepin.com","22222"));
    }

    @Test
    public void loginBadPassTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        assertFalse(GestorUsuarios.login("antoniogp68@gmail.com","patata"));
    }

    @Test
    public void loginNonexistentUsername() throws InvalidKeySpecException, NoSuchAlgorithmException {
        assertFalse(GestorUsuarios.login("idfnidnijnvdfvjdjdnkjvdfbv","patata"));
    }
}
