package infrastructure.dao;

import domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository("usuariorepository")
public interface UsuarioDAO extends JpaRepository<Usuario, Serializable> {

    Usuario findByUsername(String username);
    Long deleteByEmail(String email);

    @Query("SELECT u FROM Usuario as u WHERE u.email = :email and u.password = :password")
    Usuario loginUser(@Param("email") String email, @Param("password") String password);

}
