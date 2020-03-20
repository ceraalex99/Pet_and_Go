package io.github.ceraalex99.petandgo;

import Entities.Usuario;
import com.ja.security.PasswordHash;
import hibernate.BD.UsuariosBD;
import org.apache.commons.lang3.RandomStringUtils;
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
        String nombre = RandomStringUtils.random(25, true, false);
        String username = RandomStringUtils.random(10, true, true);
        String email = RandomStringUtils.random(30, true, true);
        String password = RandomStringUtils.random(10, true, true);
        gu.signUp(nombre,username,password,email);


        Usuario user = new Usuario();
        UsuariosBD usuariosBD = new UsuariosBD();
        List<Usuario> users = usuariosBD.getAll();
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                user = users.get(i);
            }
        }

        assertEquals(nombre, user.getNombre());
        assertEquals(username, user.getUsername());
        assertEquals(email,user.getEmail());
        assertTrue(new PasswordHash().validatePassword(password, user.getPassword()));
    }
}
