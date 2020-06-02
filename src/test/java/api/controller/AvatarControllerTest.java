package api.controller;

import api.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Avatar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AvatarController.class)
public class AvatarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AvatarServices avatarServices;

    @MockBean
    private UsuarioServices usuarioServices;


    @Test
    public void getAvatares() throws Exception {
        Avatar a1 = new Avatar("a1", 1);
        Avatar a2 = new Avatar("a2", 2);
        List<Avatar> avatares = Arrays.asList(a1, a2);
        given(avatarServices.findAllAvatar()).willReturn(avatares);

        mvc.perform(get("/api/avatares").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void postAvatar() throws Exception {
        Avatar a1 = new Avatar("a1", 1);
        given(avatarServices.altaAvatar(a1)).willReturn(true);

        String json = new ObjectMapper().writeValueAsString(a1);

        mvc.perform(post("/api/avatares").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

}
