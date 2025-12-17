package cat.itacademy.s04.t01.userapi.controllers;


import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.service.UserService;

import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping ("/users")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }



    @PostMapping
    public User create (@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping
    public List<User> getAll(@RequestParam(required = false) String name){

        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping(params = "name")
    public Optional<User> getUsersByName(@RequestParam String name) {
        return userService.findByName(name);
    }
}
