package cz.osu.project_todoholecekp_hrtonm.Controller;

import cz.osu.project_todoholecekp_hrtonm.Model.User;
import cz.osu.project_todoholecekp_hrtonm.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/users")
    public User create(@Valid @RequestBody User newUser) {
        return userService.create(newUser);
    }
    @GetMapping("/users/{name}")
    public User get(@PathVariable("name") String name) {
        return userService.get(name);
    }
    @DeleteMapping("/users/{name}")
    public void delete(@PathVariable("name") String name) throws Exception {
        userService.delete(name);
    }
    @GetMapping("/users/{name}{password}")
    public User register(@PathVariable("name") String name,@PathVariable("password") String password) {
        return userService.register(name, password);
    }
}
