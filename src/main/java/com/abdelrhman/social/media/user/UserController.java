package com.abdelrhman.social.media.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }


    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        User user = service.findUser(id);
        if (user == null)
            throw new UserNotFoundException("id " + id);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        // /users/{id} -> user.id
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        //the location of the created resource
        return ResponseEntity.created(location).build();
    }

}
