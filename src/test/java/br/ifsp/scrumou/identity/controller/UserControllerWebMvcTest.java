package br.ifsp.scrumou.identity.controller;

import br.ifsp.scrumou.identity.dto.UserResponse;
import br.ifsp.scrumou.identity.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerWebMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserService service;

    @Test
    void create_returnsCreatedJson() throws Exception {
        String jsonReq = "{" +
                "\"name\":\"Bob\"," +
                "\"email\":\"bob@example.com\"," +
                "\"token\":\"tok12345678\"," +
                "\"userType\":\"PM\"}";

        UserResponse resp = new UserResponse();
        resp.id = 2L;
        resp.name = "Bob";
        resp.email = "bob@example.com";
        resp.token = "tok12345678";
        resp.userType = "PM";

        when(service.createUser(any())).thenReturn(resp);

        String expected = mapper.writeValueAsString(resp);

        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonReq))
                .andExpect(status().isCreated())
                .andExpect(content().json(expected));
    }
}
