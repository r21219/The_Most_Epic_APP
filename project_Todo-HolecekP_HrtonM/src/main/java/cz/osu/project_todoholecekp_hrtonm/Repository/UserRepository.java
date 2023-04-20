package cz.osu.project_todoholecekp_hrtonm.Repository;

import cz.osu.project_todoholecekp_hrtonm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByNameAndPassword(String name, String password);
}
