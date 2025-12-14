package br.ifsp.scrumou.identity.controller;

import br.ifsp.scrumou.identity.dto.UserRequest;
import br.ifsp.scrumou.identity.dto.UserResponse;
import br.ifsp.scrumou.identity.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class UserControllerUnitTest {

    @Test
    void create_returnsCreatedResponse() {
        UserService service = Mockito.mock(UserService.class);
        UserController controller = new UserController(service);

        UserRequest req = new UserRequest();
        req.name = "Alice";
        req.email = "alice@example.com";
        req.token = "sometoken123";
        req.userType = "DEVELOPER";

        UserResponse resp = new UserResponse();
        resp.id = 1L;
        resp.name = req.name;
        resp.email = req.email;
        resp.token = req.token;
        resp.userType = req.userType;

        Mockito.when(service.createUser(any())).thenReturn(resp);

        ResponseEntity<UserResponse> result = controller.create(req);

        assertEquals(201, result.getStatusCode().value());
        assertEquals(resp, result.getBody());
    }
}
