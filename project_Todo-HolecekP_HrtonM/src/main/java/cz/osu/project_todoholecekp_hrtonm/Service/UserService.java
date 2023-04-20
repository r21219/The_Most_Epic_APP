package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Model.User;

import java.util.List;

public interface UserService {
    User create(User newUser);
    User get(String name);
    User search(String name);
    void update(User User) throws Exception;
    void delete(String name) throws Exception;
}
