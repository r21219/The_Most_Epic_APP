package cz.osu.project_todoholecekp_hrtonm.Repository;

import cz.osu.project_todoholecekp_hrtonm.Model.Todo;
import cz.osu.project_todoholecekp_hrtonm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findAllByTitleContainsIgnoreCase(String title);
}
