package infrastructure;

import com.ja.security.PasswordHash;
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


    private static PasswordHash passwordHash = new PasswordHash();

    public static String decodeJWT(String jwt){
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("1234")).parseClaimsJws(jwt).getBody();
        return claims.getSubject().substring(13);
    }

    public static String createToken(String email){
        return Jwts.builder().setSubject("Autorizado a " +email).signWith(SignatureAlgorithm.HS512,"1234").compact();
    }

    public static String hashedPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return passwordHash.createHash(password);
    }

    public static boolean validatePassword(String password1, String password2) throws InvalidKeySpecException, NoSuchAlgorithmException {
            return passwordHash.validatePassword(password1, password2);
    }

}