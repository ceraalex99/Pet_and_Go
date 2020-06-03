package api.controller;

import api.services.MensajeServices;
import api.services.UsuarioServices;
import entities.Mensaje;
import entities.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    public void getMensajesUsuario() throws Exception {
        Mensaje m1 = new Mensaje();
        m1.setCreated_at(LocalDateTime.now());
        Mensaje m2 = new Mensaje();
        m2.setCreated_at(LocalDateTime.now());
        Mensaje m3 = new Mensaje();
        m3.setCreated_at(LocalDateTime.now());
        Mensaje m4 = new Mensaje();
        m4.setCreated_at(LocalDateTime.now());
        Usuario user = new Usuario();

        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(user);
        given(mensajeServices.findBySender(user)).willReturn(new ArrayList<>(Arrays.asList(m1, m2))); 
        given(mensajeServices.findByReceiver(user)).willReturn(Arrays.asList(m3, m4));

        mvc.perform(get("/api/usuarios/a@prueba.com/mensajes").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(4)));

    }
}
