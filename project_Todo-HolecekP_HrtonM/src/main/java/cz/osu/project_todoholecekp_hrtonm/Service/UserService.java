package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Model.AuthenticationResponse;
import cz.osu.project_todoholecekp_hrtonm.Model.User;

import java.util.List;

public interface UserService {
    User get(String name);
    void delete(String name) throws Exception;
    AuthenticationResponse login(User user);
    AuthenticationResponse register(User user);
}
