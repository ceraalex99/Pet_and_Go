package api.controller;

import api.dto.ConsejoDTO;
import api.services.ConsejoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Consejo;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsejoController.class)
public class ConsejoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ConsejoServices consejoServices;

    @Test
    public void getConsejos() throws Exception {
        Consejo c1 = new Consejo();
        Consejo c2 = new Consejo();
        List<Consejo> consejos = Arrays.asList(c1, c2);
        given(consejoServices.findAllConsejos()).willReturn(consejos);

        mvc.perform(get("/api/consejos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getConsejoAleatorio() throws Exception {
        Consejo c1 = new Consejo();
        Consejo c2 = new Consejo();
        Consejo c3 = new Consejo();
        c1.setId(1);
        c2.setId(2);
        c3.setId(3);
        List<Consejo> consejos = Arrays.asList(c1, c2, c3);
        given(consejoServices.findAllConsejos()).willReturn(consejos);
        given(consejoServices.findById(1)).willReturn(c1);
        given(consejoServices.findById(2)).willReturn(c2);
        given(consejoServices.findById(3)).willReturn(c3);

        mvc.perform(get("/api/consejos/one").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(oneOf(1,2,3))));

    }

    @Test
    public void deleteConsejo() throws Exception {

        given(consejoServices.findById(1)).willReturn(new Consejo());
        given(consejoServices.deleteConsejoById(1)).willReturn(true);

        mvc.perform(delete("/api/consejos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteConsejoInexistente() throws Exception {
        given(consejoServices.findById(1)).willReturn(null);

        mvc.perform(delete("/api/consejos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void postConsejo() throws Exception {
        ConsejoDTO consejoDTO = new ConsejoDTO();
        consejoDTO.setConsejo("a");

        given(consejoServices.altaConsejo(Mockito.any(Consejo.class))).willReturn(true);

        mvc.perform(post("/api/consejos").content(new ObjectMapper().writeValueAsString(consejoDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void postConsejoFallido() throws Exception {
        ConsejoDTO consejoDTO = new ConsejoDTO();
        consejoDTO.setConsejo("a");

        given(consejoServices.altaConsejo(Mockito.any(Consejo.class))).willThrow(new PersistenceException());

        mvc.perform(post("/api/consejos").content(new ObjectMapper().writeValueAsString(consejoDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

}
