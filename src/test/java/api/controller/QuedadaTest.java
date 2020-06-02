package api.controller;

import api.dao.QuedadaDAO;
import entities.Quedada;
import entities.Usuario;
import io.github.ceraalex99.petandgo.GestorUsuarios;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackages  = "api.dao")
@EntityScan( basePackages = {"entities"} )
public class QuedadaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    @Qualifier("quedadarepository")
    private QuedadaDAO quedadaDAO;

    @Test
    public void getPendientesFinalizar() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Usuario pepe = new Usuario("pepe", "pepe46", GestorUsuarios.hashedPassword("password"), "pepe@pepe.pepe");
        entityManager.persist(pepe);
        Date before = new Date();
        before.setTime(before.getTime()-1000000000);
        Quedada quedada = new Quedada("pepe@pepe.pepe", new Date(), before, "aqui", 3.4, 1.6, "1");
        quedada.setFinalizada(false);
        entityManager.persist(quedada);
        entityManager.flush();

        List<Quedada> pendientes = quedadaDAO.getPendientesFinalizar();

        assertEquals("pepe@pepe.pepe", pendientes.get(0).getAdmin());
    }

    @Test
    public void noGetFinalizadas() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Usuario pepe = new Usuario("pepe", "pepe46", GestorUsuarios.hashedPassword("password"), "pepe@pepe.pepe");
        entityManager.persist(pepe);
        Date before = new Date();
        before.setTime(before.getTime()-1000000000);
        Quedada quedada = new Quedada("pepe@pepe.pepe", new Date(), before, "aqui", 3.4, 1.6, "1");
        quedada.setFinalizada(true);
        entityManager.persist(quedada);
        entityManager.flush();

        List<Quedada> pendientes = quedadaDAO.getPendientesFinalizar();

        assertTrue(pendientes.isEmpty());
    }

}
