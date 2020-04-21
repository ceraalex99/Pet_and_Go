package io.github.ceraalex99.petandgo;

import api.dao.UsuarioDAO;
import api.dao.UsuarioDAOImpl;
import com.ja.security.PasswordHash;
import entities.Usuario;
import hibernate.bd.UsuariosBD;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class GestorUsuarios {
    private GestorUsuarios(){
        throw new IllegalStateException("Utility class");
    }

    private static UsuarioDAO usuariosBD = new UsuarioDAOImpl();

    public static void signUp(String nombre, String username, String password, String email) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String hashedPassword = new PasswordHash().createHash(password);

        Usuario user = new Usuario(nombre, username, hashedPassword, email);
        usuariosBD.altaUsuario(user);
    }

    public static boolean login(String id, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Usuario user = usuariosBD.findByEmail(id); // ID = EMAIL
        try {
            return new PasswordHash().validatePassword(password, user.getPassword());
        }
        catch (NullPointerException e){
            return false;
        }

    }
    public static void delete(Usuario user) {
        usuariosBD.deleteUsuario(user);
    }

    public static void deleteByEmail(String email) {
        usuariosBD.deleteUsuarioByEmail(email);
    }

    public static String decodeJWT(String jwt){
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("1234")).parseClaimsJws(jwt).getBody();
        return claims.getSubject().substring(13);
    }

    public static String createToken(String email){
        return Jwts.builder().setSubject("Autorizado a " +email).signWith(SignatureAlgorithm.HS512,"1234").compact();
    }



}