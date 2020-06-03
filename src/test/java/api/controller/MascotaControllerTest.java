package api.controller;

import api.services.MascotaServices;
import api.services.UsuarioServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Mascota;
import entities.MascotaId;
import entities.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MascotaController.class)
public class MascotaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioServices usuarioServices;

    @MockBean
    private MascotaServices mascotaServices;

    @Test
    public void getMascotasUsuarioTest() throws Exception {
        Mascota m1 = new Mascota();
        Mascota m2 = new Mascota();
        Usuario user = new Usuario();
        user.addMascota(m1);
        user.addMascota(m2);
        given(usuarioServices.findByEmail("pepe")).willReturn(user);

        mvc.perform(get("/api/usuarios/pepe/mascotas").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getMascotaTest() throws Exception{
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby", "pepe");
        m.setId(mId);
        given(mascotaServices.findById(mId)).willReturn(m);


        mvc.perform(get("/api/usuarios/pepe/mascotas/Tobby").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.id.nombre", is("Tobby")));
    }

    @Test
    public void addMascotaTest() throws Exception {
        Mascota m = new Mascota();
        m.setId(new MascotaId("Tobby","a@prueba.com"));

       String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.altaMascota(m)).willReturn(true);

        mvc.perform(post("/api/usuarios/a@prueba.com/mascotas").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void updateMascotaTest() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","a@prueba.com");
        m.setId(mId);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(m);

        mvc.perform(put("/api/usuarios/a@prueba.com/mascotas/Tobby").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void deleteEvento() throws Exception {
        Usuario u = new Usuario();
        u.addMascota(new Mascota());
        given(mascotaServices.findById(new MascotaId("Tobby","a@prueba.com"))).willReturn(new Mascota());
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(u);
        given(mascotaServices.deleteMascotaById(new MascotaId("Tobby","a@prueba.com"))).willReturn(true);

        mvc.perform(delete("/api/usuarios/a@prueba.com/mascotas/Tobby")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteEventoDeOtroUsuario() throws Exception {

        Usuario u = new Usuario();

        given(mascotaServices.findById(new MascotaId("Tobby","a@prueba.com"))).willReturn(new Mascota());
        given(usuarioServices.findByEmail("pepe")).willReturn(u);
        given(mascotaServices.deleteMascotaById(new MascotaId("Tobby","a@prueba.com"))).willReturn(true);

        mvc.perform(delete("/api/usuarios/pepe/mascotas/Tobby")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

    }

    @Test
    public void updateImageTest() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","a@prueba.com");
        m.setId(mId);
        m.setPetimage(new byte[5]);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(m);

        mvc.perform(put("/api/usuarios/a@prueba.com/mascotas/Tobby/image").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getImageTest() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby", "pepe");
        m.setId(mId);
        byte[] bArray = "Hola".getBytes();
        m.setPetimage(bArray);
        given(mascotaServices.findById(mId)).willReturn(m);


        mvc.perform(get("/api/usuarios/pepe/mascotas/Tobby/image").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk()).andExpect(jsonPath("$", is(new String(bArray))));
    }
}
