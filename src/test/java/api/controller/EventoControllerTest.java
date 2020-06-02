package api.controller;

import api.services.EventoServices;
import api.services.UsuarioServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Evento;
import entities.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventoController.class)
public class EventoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioServices usuarioServices;

    @MockBean
    private EventoServices eventoServices;


    @Test
    public void getEventosUsuario() throws Exception {

        Usuario pepe = new Usuario();
        pepe.setEmail("pepe");

        Evento e1 = new Evento();
        Evento e2 = new Evento();

        pepe.addEvento(e1);
        pepe.addEvento(e2);
        given(usuarioServices.findByEmail("pepe")).willReturn(pepe);

        mvc.perform(get("/api/calendario/pepe/eventos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void createEvento() throws Exception {

        Evento e = new Evento("a","a@prueba.com",new Date(), new Date(), "a", false);
        String json = new ObjectMapper().writeValueAsString(e);
        given(eventoServices.altaEvento(e)).willReturn(true);

        mvc.perform(post("/api/calendario/a@prueba.com/eventos")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void intentarCrearEventoAOtroUsuario() throws Exception {

        Evento e = new Evento("a", "aaaaa", new Date(), new Date(), "a", false);
        String json = new ObjectMapper().writeValueAsString(e);
        given(eventoServices.altaEvento(e)).willReturn(true);

        mvc.perform(post("/api/calendario/a@prueba.com/eventos")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void deleteEvento() throws Exception {
        Usuario u = new Usuario();
        Evento e = new Evento();
        e.setId(1);
        u.addEvento(e);
        given(eventoServices.findById(1)).willReturn(e);
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(u);
        given(eventoServices.deleteEventoById(1)).willReturn(true);

        mvc.perform(delete("/api/calendario/a@prueba.com/eventos/1")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteEventoDeOtroUsuario() throws Exception {

        Usuario u = new Usuario();
        Evento e = new Evento();
        e.setId(1);
        u.addEvento(e);
        given(eventoServices.findById(1)).willReturn(e);
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(u);
        given(eventoServices.deleteEventoById(1)).willReturn(true);

        mvc.perform(delete("/api/calendario/pepe/eventos/1")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

    }

    @Test
    public void updateEvento() throws Exception {

        Evento e = new Evento("a","a@prueba.com",new Date(), new Date(), "a", false);
        String json = new ObjectMapper().writeValueAsString(e);
        given(eventoServices.findById(1)).willReturn(new Evento());


        mvc.perform(post("/api/calendario/a@prueba.com/eventos")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
}
