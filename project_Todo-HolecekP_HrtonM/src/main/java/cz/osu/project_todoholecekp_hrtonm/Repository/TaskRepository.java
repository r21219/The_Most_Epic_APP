package cz.osu.project_todoholecekp_hrtonm.Repository;

import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findAllByTitleContainsIgnoreCase(String title);
    List<Task> findByOrderByTitleAsc();
    List<Task> findByOrderByTitleDesc();
    @Query("SELECT t FROM Task t ORDER BY CASE WHEN t.deadLine IS NULL THEN 1 ELSE 0 END, t.deadLine ASC")
    List<Task> findAllByOrderByDeadlineAsc();
    @Query("SELECT t FROM Task t ORDER BY CASE WHEN t.deadLine IS NULL THEN 1 ELSE 0 END, t.deadLine DESC")
    List<Task> findAllByOrderByDeadlineDesc();

    List<Task> findAllByOrderByCompleteAsc();
    List<Task> findAllByOrderByCompleteDesc();


}
