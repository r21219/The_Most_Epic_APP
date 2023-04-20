package cz.osu.project_todoholecekp_hrtonm.Repository;

import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findAllByDescriptionContainsIgnoreCase(String description);

}
