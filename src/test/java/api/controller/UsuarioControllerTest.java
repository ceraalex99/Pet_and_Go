package api.controller;


import api.dto.LoginBody;
import api.dto.UsuarioDTO;
import api.dto.UsuarioUpdateCamposDTO;
import api.dto.UsuarioUpdatePasswordDTO;
import api.services.UsuarioServices;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(name = "usuarioservices")
    private UsuarioServices usuarioServices;

    @Test
    public void updatePass() throws Exception {
        UsuarioUpdatePasswordDTO usuarioUpdatePasswordDTO = new UsuarioUpdatePasswordDTO("new", "old");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.login(Mockito.any(String.class),Mockito.any(String.class))).willReturn(true);


        mvc.perform(put("/api/usuarios/a@prueba.com/forgot").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(usuarioUpdatePasswordDTO))).andExpect(status().isOk());
    }

    @Test
    public void updatePassOtroUsuario() throws Exception {
        UsuarioUpdatePasswordDTO usuarioUpdatePasswordDTO = new UsuarioUpdatePasswordDTO("new", "old");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.login(Mockito.any(String.class),Mockito.any(String.class))).willReturn(true);


        mvc.perform(put("/api/usuarios/e/forgot").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(usuarioUpdatePasswordDTO))).andExpect(status().isForbidden());
    }

    @Test
    public void updatePassSinAuth() throws Exception {
        UsuarioUpdatePasswordDTO usuarioUpdatePasswordDTO = new UsuarioUpdatePasswordDTO("new", "old");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.login(Mockito.any(String.class),Mockito.any(String.class))).willReturn(true);


        mvc.perform(put("/api/usuarios/a@prueba.com/forgot").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(usuarioUpdatePasswordDTO))).andExpect(status().isForbidden());
    }

    @Test
    public void updatePassInexistente() throws Exception {
        UsuarioUpdatePasswordDTO usuarioUpdatePasswordDTO = new UsuarioUpdatePasswordDTO("new", "old");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);
        given(usuarioServices.login(Mockito.any(String.class),Mockito.any(String.class))).willReturn(true);


        mvc.perform(put("/api/usuarios/a@prueba.com/forgot").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(usuarioUpdatePasswordDTO))).andExpect(status().isNotFound());
    }

    @Test
    public void updatePassIncorrecta() throws Exception {
        UsuarioUpdatePasswordDTO usuarioUpdatePasswordDTO = new UsuarioUpdatePasswordDTO("new", "old");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.login(Mockito.any(String.class),Mockito.any(String.class))).willReturn(false);


        mvc.perform(put("/api/usuarios/a@prueba.com/forgot").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(usuarioUpdatePasswordDTO))).andExpect(status().isBadRequest());
    }

    @Test
    public void updateCampos() throws Exception {
        UsuarioUpdateCamposDTO usuarioUpdateCamposDTO = new UsuarioUpdateCamposDTO("nombre", "uname");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());


        mvc.perform(put("/api/usuarios/a@prueba.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(usuarioUpdateCamposDTO))).andExpect(status().isOk());
    }

    @Test
    public void updateCamposOtroUsuario() throws Exception {
        UsuarioUpdateCamposDTO usuarioUpdateCamposDTO = new UsuarioUpdateCamposDTO("nombre", "uname");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());


        mvc.perform(put("/api/usuarios/a").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(usuarioUpdateCamposDTO))).andExpect(status().isForbidden());
    }

    @Test
    public void updateCamposSinAuth() throws Exception {
        UsuarioUpdateCamposDTO usuarioUpdateCamposDTO = new UsuarioUpdateCamposDTO("nombre", "uname");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());


        mvc.perform(put("/api/usuarios/a@prueba.com").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(usuarioUpdateCamposDTO))).andExpect(status().isForbidden());
    }

    @Test
    public void updateCamposUsuarioInexistente() throws Exception {
        UsuarioUpdateCamposDTO usuarioUpdateCamposDTO = new UsuarioUpdateCamposDTO("nombre", "uname");

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);


        mvc.perform(put("/api/usuarios/a@prueba.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content(new ObjectMapper().writeValueAsString(usuarioUpdateCamposDTO))).andExpect(status().isNotFound());
    }

    @Test
    public void getUsuarios() throws Exception {
        given(usuarioServices.findAllUsuario()).willReturn(new ArrayList<>(Arrays.asList(new Usuario(), new Usuario())));

        mvc.perform(get("/api/usuarios").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getUsuariosSinAuth() throws Exception {
        given(usuarioServices.findAllUsuario()).willReturn(new ArrayList<>(Arrays.asList(new Usuario(), new Usuario())));

        mvc.perform(get("/api/usuarios").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    public void getUsuariosSinUsuarios() throws Exception {
        given(usuarioServices.findAllUsuario()).willReturn(null);

        mvc.perform(get("/api/usuarios").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isNoContent());
    }

    @Test
    public void getUsuario() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario("a", "a", "a", "a"));

        mvc.perform(get("/api/usuarios/a@prueba.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk());
    }


    @Test
    public void getUsuarioInexistente() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);

        mvc.perform(get("/api/usuarios/a@prueba.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isNotFound());
    }

    @Test
    public void createUser() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO("a","a","a","a");
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);
        given(usuarioServices.altaUsuario(Mockito.any(Usuario.class))).willReturn(true);

        mvc.perform(post("/api/usuarios").content(new ObjectMapper().writeValueAsString(usuarioDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void createUserEmailExistente() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO("a","a","a","a");
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.altaUsuario(Mockito.any(Usuario.class))).willReturn(true);

        mvc.perform(post("/api/usuarios").content(new ObjectMapper().writeValueAsString(usuarioDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(jsonPath("$", is("email")));
    }

    @Test
    public void createUserUsernameExistente() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO("a","a","a","a");
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);
        given(usuarioServices.altaUsuario(Mockito.any(Usuario.class))).willReturn(true);
        given(usuarioServices.findByUsername(Mockito.any(String.class))).willReturn(new Usuario());

        mvc.perform(post("/api/usuarios").content(new ObjectMapper().writeValueAsString(usuarioDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(jsonPath("$", is("username")));
    }

    @Test
    public void login() throws Exception {
        given(usuarioServices.login(Mockito.any(String.class), Mockito.any())).willReturn(true);

        mvc.perform(post("/api/usuarios/login").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(new LoginBody("a", "a")))).andExpect(status().isOk());
    }

    @Test
    public void badLogin() throws Exception {
        given(usuarioServices.login(Mockito.any(String.class), Mockito.any())).willReturn(false);

        mvc.perform(post("/api/usuarios/login").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(new LoginBody("a", "a")))).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteUsuario() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.deleteUsuarioByEmail(Mockito.any(String.class))).willReturn(true);

        mvc.perform(delete("/api/usuarios/a@prueba.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk());

    }

    @Test
    public void deleteUsuarioInexistente() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);
        given(usuarioServices.deleteUsuarioByEmail(Mockito.any(String.class))).willReturn(true);

        mvc.perform(delete("/api/usuarios/a@prueba.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isNotFound());

    }

    @Test
    public void deleteOtroUsuario() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.deleteUsuarioByEmail(Mockito.any(String.class))).willReturn(true);

        mvc.perform(delete("/api/usuarios/a@a.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isForbidden());

    }

    @Test
    public void deleteUsuarioSinAuth() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.deleteUsuarioByEmail(Mockito.any(String.class))).willReturn(true);

        mvc.perform(delete("/api/usuarios/a@a.com").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

    }

    @Test
    public void deleteUsuarioFallido() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(usuarioServices.deleteUsuarioByEmail(Mockito.any(String.class))).willReturn(false);

        mvc.perform(delete("/api/usuarios/a@prueba.com").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isInternalServerError());

    }

    @Test
    public void addImage() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());

        mvc.perform(put("/api/usuarios/a@prueba.com/image").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("hola".getBytes())).andExpect(status().isOk());
    }

    @Test
    public void addImageOtroUsuario() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());

        mvc.perform(put("/api/usuarios/a@a.com/image").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("hola".getBytes())).andExpect(status().isForbidden());
    }

    @Test
    public void addImageSinAuth() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());

        mvc.perform(put("/api/usuarios/a@a.com/image").contentType(MediaType.APPLICATION_JSON).content("hola".getBytes())).andExpect(status().isForbidden());
    }

    @Test
    public void addImageUsuarioInexistente() throws Exception {

        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);

        mvc.perform(put("/api/usuarios/a@prueba.com/image").contentType(MediaType.APPLICATION_JSON).header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("hola".getBytes())).andExpect(status().isNotFound());
    }

    @Test
    public void getImage() throws Exception {
        Usuario u = new Usuario();
        u.setImage("hola".getBytes());
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(u);

        mvc.perform(get("/api/usuarios/a@prueba.com/image").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isOk());
    }

    @Test
    public void getImageSinAuth() throws Exception {
        Usuario u = new Usuario();
        u.setImage("hola".getBytes());
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(u);

        mvc.perform(get("/api/usuarios/a@prueba.com/image")).andExpect(status().isUnauthorized());
    }

    @Test
    public void getImageUsuarioInexistente() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);

        mvc.perform(get("/api/usuarios/a@prueba.com/image").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA")).andExpect(status().isNotFound());
    }

    @Test
    public void getFirebase() throws Exception {
        Usuario u = new Usuario();
        u.setFirebaseToken("hola");
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(u);

        mvc.perform(get("/api/usuarios/a@prueba.com/firebase").header("Authorization", "8jGerhqiOlLokORRMEx1WJqx0kCNqqXA")).andExpect(status().isOk());
    }

    @Test
    public void getFirebaseSinAuth() throws Exception {
        Usuario u = new Usuario();
        u.setFirebaseToken("hola");
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(u);

        mvc.perform(get("/api/usuarios/a@prueba.com/firebase")).andExpect(status().isUnauthorized());
    }

    @Test
    public void getFirebaseExterno() throws Exception {
        Usuario u = new Usuario();
        u.setFirebaseToken("hola");
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(u);

        mvc.perform(get("/api/usuarios/a@prueba.com/firebase").header("Authorization", "8jGerhqiO0kCNqqXA")).andExpect(status().isForbidden());
    }

    @Test
    public void getFirebaseUsuarioInexistente() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(null);

        mvc.perform(get("/api/usuarios/a@prueba.com/firebase").header("Authorization", "8jGerhqiOlLokORRMEx1WJqx0kCNqqXA")).andExpect(status().isNotFound());
    }
}
