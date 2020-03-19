package io.github.ceraalex99.petandgo;

import Entities.Usuario;
import com.ja.security.PasswordHash;
import hibernate.BD.UsuariosBD;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GestorUsuariosTest {

    @Test
    public void signUpTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        GestorUsuarios gu = new GestorUsuarios();
        int id = gu.signUp("Antonio","Garcia","Perez","antonio68","123456abc","antoniogp68@gmail.com");

        UsuariosBD usuariosBD = new UsuariosBD();
        Usuario user = usuariosBD.get(id);
        assertEquals("Antonio", user.getNombre());
        assertEquals("Garcia", user.getApellido1());
        assertEquals("Perez", user.getApellido2());
        assertEquals("antonio68", user.getUsername());
        assertEquals("antoniogp68@gmail.com",user.getEmail());
        assertTrue(new PasswordHash().validatePassword("123456abc", user.getPassword()));
    }
}
