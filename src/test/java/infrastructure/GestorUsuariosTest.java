package infrastructure;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class GestorUsuariosTest {

    @Test
    public void decodeJWT() {
        String email = "joan@gmail.com";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgam9hbkBnbWFpbC5jb20ifQ.PhPNnUE_cL3tAqJ_DrpWP5h903NXWMTRgoXCYdUCkWlFYvP5encnzDVIj4CQfiQUqQ2m751SW_hqUXDc5c4mUA";
        String emailToken = GestorUsuarios.decodeJWT(token);
        assertEquals(email, emailToken);
    }

    @Test
    public void createToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgam9hbkBnbWFpbC5jb20ifQ.PhPNnUE_cL3tAqJ_DrpWP5h903NXWMTRgoXCYdUCkWlFYvP5encnzDVIj4CQfiQUqQ2m751SW_hqUXDc5c4mUA";
        String email = "joan@gmail.com";
        String tokenCreate = GestorUsuarios.createToken(email);
        assertEquals(token, tokenCreate);
    }

    @Test
    public void validatePassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String pass = "pepe123";
        String passHashed = "20000:1bd482f4c6aa030b3502bc36b9fd5998a7ce5e674af39ddf:8c772d54d69035d9a67d15ccec182879ba6461e3e7c32fd5";
        boolean okey = GestorUsuarios.validatePassword(pass,passHashed);
        assertTrue(okey);
        okey = GestorUsuarios.validatePassword("blabla",passHashed);
        assertFalse(okey);
    }

    @Test
    public void hashedPassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String pass = "pepe123";
        String passHashed = GestorUsuarios.hashedPassword(pass);
        boolean okey = GestorUsuarios.validatePassword(pass,passHashed);
        assertTrue(okey);
    }

}

