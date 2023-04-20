package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Model.User;

import java.util.List;

public interface UserService {
    User create(User newUser);
    User get(String name);
    void delete(String name) throws Exception;
}
