package cat.itacademy.s04.t01.userapi.controllers;


import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/users")
public class UserController {
    private static List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getUsers(){
        return users;
    }

    @PostMapping
    public User createUser (@RequestBody User user){
        user.setId(UUID.randomUUID());
        users.add(user);
        return user;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "name")
    public List<User> getUsersByName(@RequestParam String name) {
        return users.stream()
                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
