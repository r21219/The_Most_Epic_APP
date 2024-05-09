package cz.osu.project_todoholecekp_hrtonm.Controller;

import cz.osu.project_todoholecekp_hrtonm.Model.User;
import cz.osu.project_todoholecekp_hrtonm.Service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{name}")
    public User get(@PathVariable("name") String name) {
        return userService.get(name);
    }

    @DeleteMapping("/users/{name}")
    public void delete(@PathVariable("name") String name) throws Exception {
        userService.delete(name);
    }
}
