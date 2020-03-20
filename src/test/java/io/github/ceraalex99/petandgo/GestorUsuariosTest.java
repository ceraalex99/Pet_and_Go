package io.github.ceraalex99.petandgo;

import Entities.Usuario;
import com.ja.security.PasswordHash;
import hibernate.BD.UsuariosBD;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GestorUsuariosTest {

    @Test
    public void signUpTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        GestorUsuarios gu = new GestorUsuarios();
        gu.signUp("Antonio Garcia Perez","antonio68","123456abc","antoniogp68@gmail.com");


        Usuario user = new Usuario();
        UsuariosBD usuariosBD = new UsuariosBD();
        List<Usuario> users = usuariosBD.getAll();
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals("antonio68")){
                user = users.get(i);
            }
        }

        assertEquals("Antonio Garcia Perez", user.getNombre());
        assertEquals("antonio68", user.getUsername());
        assertEquals("antoniogp68@gmail.com",user.getEmail());
        assertTrue(new PasswordHash().validatePassword("123456abc", user.getPassword()));
    }
}
