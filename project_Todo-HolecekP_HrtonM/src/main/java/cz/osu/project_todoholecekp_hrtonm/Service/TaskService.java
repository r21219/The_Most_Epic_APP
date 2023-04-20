package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Model.Task;

import java.util.List;

public interface TaskService {
    Task create(Task newTask);
    Task get(long id);
    List<Task> getAll();
    Task search(String name);
    void update(Task task) throws Exception;
    void delete(long id) throws Exception;

}
