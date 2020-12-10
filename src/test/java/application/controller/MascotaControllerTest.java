package application.controller;

import application.services.MascotaServices;
import application.services.UsuarioServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Mascota;
import domain.models.MascotaId;
import domain.models.Usuario;
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
    public void getMascotasUsuarioInexistente() throws Exception {
        Mascota m1 = new Mascota();
        Mascota m2 = new Mascota();
        Usuario user = new Usuario();
        user.addMascota(m1);
        user.addMascota(m2);
        given(usuarioServices.findByEmail("pepe")).willReturn(null);

        mvc.perform(get("/api/usuarios/pepe/mascotas").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
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
    public void getMascotaInexistente() throws Exception{
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby", "pepe");
        m.setId(mId);
        given(mascotaServices.findById(mId)).willReturn(null);


        mvc.perform(get("/api/usuarios/pepe/mascotas/Tobby").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
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
    public void addMascotaOtroUsuario() throws Exception {
        Mascota m = new Mascota();
        m.setId(new MascotaId("Tobby","e"));

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.altaMascota(m)).willReturn(true);

        mvc.perform(post("/api/usuarios/e/mascotas").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void addMascotaSinAuth() throws Exception {
        Mascota m = new Mascota();
        m.setId(new MascotaId("Tobby","a@prueba.com"));

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.altaMascota(m)).willReturn(true);

        mvc.perform(post("/api/usuarios/a@prueba.com/mascotas").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void addMascotaFallida() throws Exception {
        Mascota m = new Mascota();
        m.setId(new MascotaId("Tobby","a@prueba.com"));

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.altaMascota(Mockito.any(Mascota.class))).willThrow(new PersistenceException());

        mvc.perform(post("/api/usuarios/a@prueba.com/mascotas").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
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
    public void updateMascotaUsuarioInconsistente() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","pepe");
        m.setId(mId);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(m);

        mvc.perform(put("/api/usuarios/a@prueba.com/mascotas/Tobby").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void updateMascotaNombreInconsistente() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","pepe");
        m.setId(mId);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(m);

        mvc.perform(put("/api/usuarios/a@prueba.com/mascotas/Matias").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void updateMascotaOtroUsuario() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","e");
        m.setId(mId);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(m);

        mvc.perform(put("/api/usuarios/e/mascotas/Tobby").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void updateMascotaSinAuth() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","e");
        m.setId(mId);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(m);

        mvc.perform(put("/api/usuarios/e/mascotas/Tobby").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void updateMascotaInexistente() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","a@prueba.com");
        m.setId(mId);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(null);

        mvc.perform(put("/api/usuarios/a@prueba.com/mascotas/Tobby").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void deleteMascota() throws Exception {
        Usuario u = new Usuario();
        u.addMascota(new Mascota());
        given(mascotaServices.findById(new MascotaId("Tobby","a@prueba.com"))).willReturn(new Mascota());
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(u);
        given(mascotaServices.deleteMascotaById(new MascotaId("Tobby","a@prueba.com"))).willReturn(true);

        mvc.perform(delete("/api/usuarios/a@prueba.com/mascotas/Tobby")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteMascotaInexistente() throws Exception {
        Usuario u = new Usuario();
        u.addMascota(new Mascota());
        given(mascotaServices.findById(new MascotaId("Tobby","a@prueba.com"))).willReturn(null);
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(u);
        given(mascotaServices.deleteMascotaById(new MascotaId("Tobby","a@prueba.com"))).willReturn(true);

        mvc.perform(delete("/api/usuarios/a@prueba.com/mascotas/Tobby")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

    }

    @Test
    public void deleteMascotaDeOtroUsuario() throws Exception {

        Usuario u = new Usuario();

        given(mascotaServices.findById(new MascotaId("Tobby","a@prueba.com"))).willReturn(new Mascota());
        given(usuarioServices.findByEmail("pepe")).willReturn(u);
        given(mascotaServices.deleteMascotaById(new MascotaId("Tobby","a@prueba.com"))).willReturn(true);

        mvc.perform(delete("/api/usuarios/pepe/mascotas/Tobby")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

    }

    @Test
    public void deleteMascotaSinAuth() throws Exception {

        Usuario u = new Usuario();

        given(mascotaServices.findById(new MascotaId("Tobby","a@prueba.com"))).willReturn(new Mascota());
        given(usuarioServices.findByEmail("pepe")).willReturn(u);
        given(mascotaServices.deleteMascotaById(new MascotaId("Tobby","a@prueba.com"))).willReturn(true);

        mvc.perform(delete("/api/usuarios/pepe/mascotas/Tobby").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

    }

    @Test
    public void deleteMascotaFallido() throws Exception {
        Usuario u = new Usuario();
        u.addMascota(new Mascota());
        given(mascotaServices.findById(new MascotaId("Tobby","a@prueba.com"))).willReturn(new Mascota());
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(u);
        given(mascotaServices.deleteMascotaById(Mockito.any(MascotaId.class))).willReturn(false);

        mvc.perform(delete("/api/usuarios/a@prueba.com/mascotas/Tobby")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());

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
    public void updateImageOtroUsuario() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","e");
        m.setId(mId);
        m.setPetimage(new byte[5]);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(m);

        mvc.perform(put("/api/usuarios/e/mascotas/Tobby/image").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void updateImageSinAuth() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","e");
        m.setId(mId);
        m.setPetimage(new byte[5]);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(m);

        mvc.perform(put("/api/usuarios/e/mascotas/Tobby/image").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void updateImageInexistente() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby","a@prueba.com");
        m.setId(mId);
        m.setPetimage(new byte[5]);

        String json = new ObjectMapper().writeValueAsString(m);
        given(mascotaServices.findById(mId)).willReturn(null);

        mvc.perform(put("/api/usuarios/a@prueba.com/mascotas/Tobby/image").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
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

    @Test
    public void getImageSinAuth() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby", "pepe");
        m.setId(mId);
        byte[] bArray = "Hola".getBytes();
        m.setPetimage(bArray);
        given(mascotaServices.findById(mId)).willReturn(m);


        mvc.perform(get("/api/usuarios/pepe/mascotas/Tobby/image")).andExpect(status().isForbidden());
    }

    @Test
    public void getImageMascotaInexistente() throws Exception {
        Mascota m = new Mascota();
        MascotaId mId = new MascotaId("Tobby", "pepe");
        m.setId(mId);
        byte[] bArray = "Hola".getBytes();
        m.setPetimage(bArray);
        given(mascotaServices.findById(mId)).willReturn(null);


        mvc.perform(get("/api/usuarios/pepe/mascotas/Tobby/image").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isNotFound());
    }
}
