package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Model.SortingType;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;

import java.util.List;

public interface TaskService {
    Task create(Task newTask);
    Task get(long id);
    List<Task> getAll();
    List<Task> search(String title);
    Task  update(Task task) throws Exception;
    void delete(long id) throws Exception;
    //Method for getting a sorted list of tasks based on input
    List<Task> sortedGet(long categoryId,SortingType sortingType);
}
