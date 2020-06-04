package api.controller;

import api.services.MyFriendsServices;
import api.services.UsuarioServices;
import entities.MyFriendsId;
import entities.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyFriendsController.class)
public class MyFriendsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(name="myfriendsservices")
    private MyFriendsServices myfriendsServices;

    @MockBean(name="usuarioservices")
    private UsuarioServices usuarioServices;

    @Test
    public void addAmigo() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());

        mvc.perform(post("/api/amigos/a@prueba.com/Addamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isCreated());
    }

    @Test
    public void addAmigoYaExistente() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(myfriendsServices.existeRelacion(Mockito.any(MyFriendsId.class))).willReturn(true);

        mvc.perform(post("/api/amigos/a@prueba.com/Addamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isBadRequest());
    }

    @Test
    public void addAmigoASiMismo() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());

        mvc.perform(post("/api/amigos/a@prueba.com/Addamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("a@prueba.com")).andExpect(status().isBadRequest());
    }

    @Test
    public void addAmigoUsuarioNoExistente() throws Exception {
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(null);
        given(usuarioServices.findByEmail("pepe")).willReturn(new Usuario());

        mvc.perform(post("/api/amigos/a@prueba.com/Addamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isNotFound());
    }

    @Test
    public void addAmigoNoExistente() throws Exception {
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(new Usuario());
        given(usuarioServices.findByEmail("pepe")).willReturn(null);

        mvc.perform(post("/api/amigos/a@prueba.com/Addamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isNotFound());
    }

    @Test
    public void addAmigoSinAuth() throws Exception {
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(null);
        given(usuarioServices.findByEmail("pepe")).willReturn(new Usuario());

        mvc.perform(post("/api/amigos/a@prueba.com/Addamic").content("pepe")).andExpect(status().isForbidden());
    }

    @Test
    public void addAmigoOtroUsuario() throws Exception {
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(null);
        given(usuarioServices.findByEmail("pepe")).willReturn(new Usuario());

        mvc.perform(post("/api/amigos/a/Addamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isForbidden());
    }

    @Test
    public void borrarAmigo() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(myfriendsServices.existeRelacion(Mockito.any(MyFriendsId.class))).willReturn(true);

        mvc.perform(post("/api/amigos/a@prueba.com/Removeamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isOk());
    }

    @Test
    public void borrarAmigoNoAmigo() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());

        mvc.perform(post("/api/amigos/a@prueba.com/Removeamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isBadRequest());
    }

    @Test
    public void borrarAmigoNoExistente() throws Exception {
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(new Usuario());
        given(usuarioServices.findByEmail("pepe")).willReturn(null);
        given(myfriendsServices.existeRelacion(Mockito.any(MyFriendsId.class))).willReturn(true);

        mvc.perform(post("/api/amigos/a@prueba.com/Removeamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isNotFound());
    }

    @Test
    public void borrarAmigoUsuarioNoExistente() throws Exception {
        given(usuarioServices.findByEmail("a@prueba.com")).willReturn(null);
        given(usuarioServices.findByEmail("pepe")).willReturn(new Usuario());
        given(myfriendsServices.existeRelacion(Mockito.any(MyFriendsId.class))).willReturn(true);

        mvc.perform(post("/api/amigos/a@prueba.com/Removeamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isNotFound());
    }

    @Test
    public void borrarAmigoOtroUsuario() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(myfriendsServices.existeRelacion(Mockito.any(MyFriendsId.class))).willReturn(true);

        mvc.perform(post("/api/amigos/a@a.com/Removeamic").header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRvcml6YWRvIGEgYUBwcnVlYmEuY29tIn0.-LUSfD27LzpSCy8RRBV5FBrtrhObgERJlAkO_8mk8E0JHVlabEjveloL3Al5g82n_7fHX1ciVazTj1YV9xrkJA").content("pepe")).andExpect(status().isForbidden());
    }

    @Test
    public void borrarAmigoSinAuth() throws Exception {
        given(usuarioServices.findByEmail(Mockito.any(String.class))).willReturn(new Usuario());
        given(myfriendsServices.existeRelacion(Mockito.any(MyFriendsId.class))).willReturn(true);

        mvc.perform(post("/api/amigos/a@prueba.com/Removeamic").content("pepe")).andExpect(status().isForbidden());
    }
}
