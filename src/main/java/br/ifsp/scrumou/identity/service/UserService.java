package br.ifsp.scrumou.identity.service;

import br.ifsp.scrumou.identity.dto.UserRequest;
import br.ifsp.scrumou.identity.dto.UserResponse;
import br.ifsp.scrumou.identity.model.User;
import br.ifsp.scrumou.identity.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse createUser(UserRequest req) {
        User user = new User();
        user.name = req.name;
        user.email = req.email;
        user.token = req.token;
        user.userType = User.UserType.valueOf(req.userType);
        User saved = repository.save(user);

        UserResponse resp = new UserResponse();
        resp.id = saved.id;
        resp.name = saved.name;
        resp.email = saved.email;
        resp.token = saved.token;
        resp.userType = saved.userType.name();
        return resp;
    }

    public UserResponse getUserById(Long id) {
        User user = repository.findById(id).orElseThrow();

        UserResponse resp = new UserResponse();
        resp.id = user.id;
        resp.name = user.name;
        resp.email = user.email;
        resp.token = user.token;
        resp.userType = user.userType.name();
        return resp;
    }

    public UserResponse getUserByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow();

        UserResponse resp = new UserResponse();
        resp.id = user.id;
        resp.name = user.name;
        resp.email = user.email;
        resp.token = user.token;
        resp.userType = user.userType.name();
        return resp;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = repository.findAll();
        List<UserResponse> resp = new ArrayList<>();
        for (User user : users) {
            UserResponse userResp = new UserResponse();
            userResp.id = user.id;
            userResp.name = user.name;
            userResp.email = user.email;
            userResp.token = user.token;
            userResp.userType = user.userType.name();
            resp.add(userResp);
        }
        return resp;
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public UserResponse updateUser(Long id, UserRequest req) {
        User user = repository.findById(id).orElseThrow();
        user.name = req.name;
        user.email = req.email;
        user.token = req.token;
        user.userType = User.UserType.valueOf(req.userType);
        User updated = repository.save(user);

        UserResponse resp = new UserResponse();
        resp.id = updated.id;
        resp.name = updated.name;
        resp.email = updated.email;
        resp.token = updated.token;
        resp.userType = updated.userType.name();
        return resp;
    }
}