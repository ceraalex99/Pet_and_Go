package api.controller;

import api.dto.MascotaIdDTO;
import api.dto.QuedadaDTO;
import api.services.MascotaServices;
import api.services.MyFriendsServices;
import api.services.QuedadaServices;
import api.services.UsuarioServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Mascota;
import entities.MascotaId;
import entities.Quedada;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuedadaController.class)
public class QuedadaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(name = "usuarioservices")
    private UsuarioServices usuarioServices;

    @MockBean
    private QuedadaServices quedadaServices;

    @MockBean
    private MascotaServices mascotaServices;

    @MockBean
    private MyFriendsServices myFriendsServices;

    @Test
    public void getQuedadasPart() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());


        mvc.perform(get("/api/quedadas").queryParam("participante", "part")).andExpect(status().isNoContent());
    }

    @Test
    public void getQuedadasPartInexistente() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);


        mvc.perform(get("/api/quedadas").queryParam("participante", "part")).andExpect(status().isNotFound());
    }

    @Test
    public void getQuedadasOrder() throws Exception {
        Quedada q1 = new Quedada();
        q1.setFechaQuedada(new Date());
        Quedada q2 = new Quedada();
        q2.setFechaQuedada(new Date(1999999999));

        given(quedadaServices.findAllQuedada()).willReturn(new ArrayList<>(Arrays.asList(q1, q2)));

        mvc.perform(get("/api/quedadas").queryParam("order", "time")).andExpect(status().isOk());
    }

    @Test
    public void getQuedadasAdmin() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());

        mvc.perform(get("/api/quedadas").queryParam("admin", "admin")).andExpect(status().isNoContent());
    }

    @Test
    public void getQuedadasAdminInexistente() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);

        mvc.perform(get("/api/quedadas").queryParam("admin", "admin")).andExpect(status().isNotFound());
    }

    @Test
    public void getQuedada() throws Exception {
        given(quedadaServices.findById(1)).willReturn(new Quedada());

        mvc.perform(get("/api/quedadas/1")).andExpect(status().isOk());
    }

    @Test
    public void getQuedadaInexistente() throws Exception {
        given(quedadaServices.findById(1)).willReturn(null);

        mvc.perform(get("/api/quedadas/1")).andExpect(status().isNotFound());
    }

    @Test
    public void getQuedadasPorDistancia() throws Exception {
        Quedada q1 = new Quedada();
        q1.setFechaQuedada(new Date());
        Quedada q2 = new Quedada();
        q2.setFechaQuedada(new Date());
        List<Quedada> quedadas = Arrays.asList(q1, q2);

        given(quedadaServices.findAllQuedada()).willReturn(quedadas);

        mvc.perform(get("/api/quedadas/distancia/10/2/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk());
    }

    @Test
    public void getQuedadasPorDistanciaSinAuth() throws Exception {
        Quedada q1 = new Quedada();
        q1.setFechaQuedada(new Date());
        Quedada q2 = new Quedada();
        q2.setFechaQuedada(new Date());
        List<Quedada> quedadas = Arrays.asList(q1, q2);

        given(quedadaServices.findAllQuedada()).willReturn(quedadas);

        mvc.perform(get("/api/quedadas/distancia/10/2/1")).andExpect(status().isForbidden());
    }

    @Test
    public void addParticipante() throws Exception {
        MascotaIdDTO mascotaIdDTO = new MascotaIdDTO("tobby", "a@prueba.com");
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(new Mascota());
        given(quedadaServices.findById(1)).willReturn(new Quedada());

        mvc.perform(post("/api/quedadas/1/participantes").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(mascotaIdDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void addParticipanteOtroUsuario() throws Exception {
        MascotaIdDTO mascotaIdDTO = new MascotaIdDTO("tobby", "a@a.com");
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(new Mascota());
        given(quedadaServices.findById(1)).willReturn(new Quedada());

        mvc.perform(post("/api/quedadas/1/participantes").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(mascotaIdDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void addParticipanteMascotaInexistente() throws Exception {
        MascotaIdDTO mascotaIdDTO = new MascotaIdDTO("tobby", "a@prueba.com");
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(null);
        given(quedadaServices.findById(1)).willReturn(new Quedada());

        mvc.perform(post("/api/quedadas/1/participantes").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(mascotaIdDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void addParticipanteQuedadaInexistente() throws Exception {
        MascotaIdDTO mascotaIdDTO = new MascotaIdDTO("tobby", "a@prueba.com");
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(new Mascota());
        given(quedadaServices.findById(1)).willReturn(null);

        mvc.perform(post("/api/quedadas/1/participantes").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(mascotaIdDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void getParticipantesVacio() throws Exception {
        given(quedadaServices.findById(1)).willReturn(new Quedada());

        mvc.perform(get("/api/quedadas/1/participantes")).andExpect(status().isNoContent());
    }

    @Test
    public void getParticipantes() throws Exception {
        Quedada quedada = new Quedada();
        quedada.addParticipante(new Mascota());
        given(quedadaServices.findById(1)).willReturn(quedada);

        mvc.perform(get("/api/quedadas/1/participantes")).andExpect(status().isOk());
    }

    @Test
    public void getParticipantesQuedadaInexistente() throws Exception {
        given(quedadaServices.findById(1)).willReturn(null);

        mvc.perform(get("/api/quedadas/1/participantes")).andExpect(status().isNotFound());
    }

    @Test
    public void deleteParticipante() throws Exception {
        Mascota mascota = new Mascota();
        mascota.addQuedadaPart(new Quedada());
        given(quedadaServices.findById(1)).willReturn(new Quedada());
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(mascota);

        mvc.perform(delete("/api/quedadas/1/participantes/a@prueba.com/mascotas/Tobby").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk());
    }

    @Test
    public void deleteParticipanteOtroUsuario() throws Exception {
        Mascota mascota = new Mascota();
        mascota.addQuedadaPart(new Quedada());
        given(quedadaServices.findById(1)).willReturn(new Quedada());
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(mascota);

        mvc.perform(delete("/api/quedadas/1/participantes/a@a.com/mascotas/Tobby").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isForbidden());
    }

    @Test
    public void deleteParticipanteSinAuth() throws Exception {
        Mascota mascota = new Mascota();
        mascota.addQuedadaPart(new Quedada());
        given(quedadaServices.findById(1)).willReturn(new Quedada());
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(mascota);

        mvc.perform(delete("/api/quedadas/1/participantes/a@prueba.com/mascotas/Tobby")).andExpect(status().isForbidden());
    }

    @Test
    public void deleteParticipanteQuedadaInexistente() throws Exception {
        Mascota mascota = new Mascota();
        mascota.addQuedadaPart(new Quedada());
        given(quedadaServices.findById(1)).willReturn(null);
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(mascota);

        mvc.perform(delete("/api/quedadas/1/participantes/a@prueba.com/mascotas/Tobby").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isNotFound());
    }

    @Test
    public void deleteParticipanteMascotaInexistente() throws Exception {
        given(quedadaServices.findById(1)).willReturn(new Quedada());
        given(mascotaServices.findById(Mockito.any(MascotaId.class))).willReturn(null);

        mvc.perform(delete("/api/quedadas/1/participantes/a@prueba.com/mascotas/Tobby").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isNotFound());
    }

    @Test
    public void createQuedada() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@prueba.com", new Date(), "a", "a", 2.0, 1.0);

        mvc.perform(post("/api/quedadas").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isCreated());
    }

    @Test
    public void createQuedadaFallida() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@prueba.com", new Date(), "a", "a", 2.0, 1.0);

        given(quedadaServices.altaQuedada(Mockito.any(Quedada.class))).willReturn(-1);
        mvc.perform(post("/api/quedadas").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isInternalServerError());
    }

    @Test
    public void createQuedadaOtroUsuario() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@a.com", new Date(), "a", "a", 2.0, 1.0);

        mvc.perform(post("/api/quedadas").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isForbidden());
    }

    @Test
    public void createQuedadaSinAuth() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@prueba.com", new Date(), "a", "a", 2.0, 1.0);

        mvc.perform(post("/api/quedadas").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isForbidden());
    }

    @Test
    public void updateQuedada() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@prueba.com", new Date(Long.MAX_VALUE), "a", "a", 2.0, 1.0);

        Quedada quedada = new Quedada();
        quedada.setFechaQuedada(new Date(Long.MAX_VALUE));
        given(quedadaServices.findById(1)).willReturn(quedada);

        mvc.perform(put("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isOk());
    }

    @Test
    public void updateQuedadaOtroAdmin() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@a.com", new Date(Long.MAX_VALUE), "a", "a", 2.0, 1.0);

        given(quedadaServices.findById(1)).willReturn(new Quedada());

        mvc.perform(put("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isForbidden());
    }

    @Test
    public void updateQuedadaSinAuth() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@prueba.com", new Date(Long.MAX_VALUE), "a", "a", 2.0, 1.0);

        given(quedadaServices.findById(1)).willReturn(new Quedada());

        mvc.perform(put("/api/quedadas/1").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isForbidden());
    }

    @Test
    public void updateQuedadaInexistente() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@prueba.com", new Date(Long.MAX_VALUE), "a", "a", 2.0, 1.0);

        given(quedadaServices.findById(1)).willReturn(null);

        mvc.perform(put("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isNotFound());
    }

    @Test
    public void updateQuedadaReciente() throws Exception {
        QuedadaDTO quedadaDTO = new QuedadaDTO("a@prueba.com", new Date(), "a", "a", 2.0, 1.0);

        Quedada quedada = new Quedada();
        quedada.setFechaQuedada(new Date());
        given(quedadaServices.findById(1)).willReturn(quedada);

        mvc.perform(put("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(quedadaDTO))).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteQuedada() throws Exception {
        Quedada quedada = new Quedada();
        quedada.setAdmin("a@prueba.com");
        given(quedadaServices.findById(1)).willReturn(quedada);
        given(quedadaServices.deleteQuedada(Mockito.any(Quedada.class))).willReturn(true);

        mvc.perform(delete("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void deleteQuedadaConParticipantes() throws Exception {
        Quedada quedada = new Quedada();
        quedada.addParticipante(new Mascota());
        quedada.setAdmin("a@prueba.com");
        given(quedadaServices.findById(1)).willReturn(quedada);


        mvc.perform(delete("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteQuedadaFallida() throws Exception {
        Quedada quedada = new Quedada();
        quedada.setAdmin("a@prueba.com");
        given(quedadaServices.findById(1)).willReturn(quedada);
        given(quedadaServices.deleteQuedada(Mockito.any(Quedada.class))).willReturn(false);

        mvc.perform(delete("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
    }

    @Test
    public void deleteQuedadaSinAuth() throws Exception {
        given(quedadaServices.findById(1)).willReturn(new Quedada());
        given(quedadaServices.deleteQuedada(Mockito.any(Quedada.class))).willReturn(true);

        mvc.perform(delete("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void deleteQuedadaOtroAdmin() throws Exception {
        Quedada quedada = new Quedada();
        quedada.setAdmin("a@a.com");
        given(quedadaServices.findById(1)).willReturn(quedada);
        given(quedadaServices.deleteQuedada(Mockito.any(Quedada.class))).willReturn(true);

        mvc.perform(delete("/api/quedadas/1").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }
}
