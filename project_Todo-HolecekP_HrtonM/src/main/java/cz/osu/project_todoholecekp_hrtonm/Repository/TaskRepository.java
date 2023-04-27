package cz.osu.project_todoholecekp_hrtonm.Repository;

import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    //Method for getting a task by a title
    List<Task> findAllByTitleContainsIgnoreCase(String title);
    //Method for getting a list of tasks ordered alphabetically by title ascending
    List<Task> findByOrderByTitleAsc();
    //Method for getting a list of tasks ordered alphabetically by title descending
    List<Task> findByOrderByTitleDesc();
    //Method for getting a list of tasks ordered by deadlines while getting nulls to the bottom ascending
    @Query("SELECT t FROM Task t ORDER BY CASE WHEN t.deadLine IS NULL THEN 1 ELSE 0 END, t.deadLine ASC")
    List<Task> findAllByOrderByDeadlineAsc();
    //Method for getting a list of tasks ordered by deadlines while getting nulls to the bottom descending
    @Query("SELECT t FROM Task t ORDER BY CASE WHEN t.deadLine IS NULL THEN 1 ELSE 0 END, t.deadLine DESC")
    List<Task> findAllByOrderByDeadlineDesc();
    //Method for getting a list of tasks ordered by completed tasks ascending
    List<Task> findAllByOrderByCompleteAsc();
    //Method for getting a list of tasks ordered by completed tasks descending
    List<Task> findAllByOrderByCompleteDesc();


}
