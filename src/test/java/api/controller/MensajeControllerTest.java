package api.controller;

import api.services.MensajeServices;
import api.services.UsuarioServices;
import entities.Mensaje;
import entities.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MensajeController.class)
public class MensajeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioServices usuarioServices;

    @MockBean
    private MensajeServices mensajeServices;

    @Test
    public void postMensaje() throws Exception {
        given(mensajeServices.altaMensaje(new Mensaje())).willReturn(true);

        mvc.perform(post("/api/mensajes").content("{}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void postMensajeFallido() throws Exception {
        given(mensajeServices.altaMensaje(Mockito.any(Mensaje.class))).willThrow(new PersistenceException());

        mvc.perform(post("/api/mensajes").content("{}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void getMensajesUsuario() throws Exception {
        Usuario pepe = new Usuario();
        Usuario yo = new Usuario();
        Usuario pedro = new Usuario();
        pedro.setEmail("pedro@a.com");
        yo.setEmail("a@prueba.com");
        pepe.setEmail("pepe");
        Mensaje m1 = new Mensaje(yo, pepe, "a", LocalDateTime.now());
        Mensaje m2 = new Mensaje(yo, pedro, "a", LocalDateTime.now());
        Mensaje m3 = new Mensaje(pepe, yo, "a", LocalDateTime.now());
        Mensaje m4 = new Mensaje(pedro, yo, "a", LocalDateTime.now());
        Usuario user = new Usuario();

        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(user);
        given(mensajeServices.findBySender(user)).willReturn(new ArrayList<>(Arrays.asList(m1, m2))); 
        given(mensajeServices.findByReceiver(user)).willReturn(Arrays.asList(m3, m4));

        mvc.perform(get("/api/usuarios/a@prueba.com/mensajes/pepe").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    public void getMensajesOtroUsuario() throws Exception {
        Usuario pepe = new Usuario();
        Usuario yo = new Usuario();
        Usuario pedro = new Usuario();
        pedro.setEmail("pedro@a.com");
        yo.setEmail("a@prueba.com");
        pepe.setEmail("pepe");
        Mensaje m1 = new Mensaje(yo, pepe, "a", LocalDateTime.now());
        Mensaje m2 = new Mensaje(yo, pedro, "a", LocalDateTime.now());
        Mensaje m3 = new Mensaje(pepe, yo, "a", LocalDateTime.now());
        Mensaje m4 = new Mensaje(pedro, yo, "a", LocalDateTime.now());
        Usuario user = new Usuario();

        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(user);
        given(mensajeServices.findBySender(user)).willReturn(new ArrayList<>(Arrays.asList(m1, m2)));
        given(mensajeServices.findByReceiver(user)).willReturn(Arrays.asList(m3, m4));

        mvc.perform(get("/api/usuarios/pepe/mensajes/a@prueba.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isForbidden());

    }

    @Test
    public void getMensajesSinAuth() throws Exception {
        Usuario pepe = new Usuario();
        Usuario yo = new Usuario();
        Usuario pedro = new Usuario();
        pedro.setEmail("pedro@a.com");
        yo.setEmail("a@prueba.com");
        pepe.setEmail("pepe");
        Mensaje m1 = new Mensaje(yo, pepe, "a", LocalDateTime.now());
        Mensaje m2 = new Mensaje(yo, pedro, "a", LocalDateTime.now());
        Mensaje m3 = new Mensaje(pepe, yo, "a", LocalDateTime.now());
        Mensaje m4 = new Mensaje(pedro, yo, "a", LocalDateTime.now());
        Usuario user = new Usuario();

        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(user);
        given(mensajeServices.findBySender(user)).willReturn(new ArrayList<>(Arrays.asList(m1, m2)));
        given(mensajeServices.findByReceiver(user)).willReturn(Arrays.asList(m3, m4));

        mvc.perform(get("/api/usuarios/pepe/mensajes/a@prueba.com").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

    }
}
