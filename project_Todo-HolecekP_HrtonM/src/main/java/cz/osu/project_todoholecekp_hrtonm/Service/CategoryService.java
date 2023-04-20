package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Model.SortingType;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Model.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category newCategory);
    Category get(long id);
    List<Category> getAll();
    Category search(String name);
    void update(Category Category) throws Exception;
    void delete(long id) throws Exception;
    //Adds task to category and task tables
    Category addTask(long categoryId, Task newTask);
    //Deletes task from category and task tables
    void deleteTask(long taskId);
    //Method for getting a sorted list of categories based on input
    List<Category> sortedGet(SortingType sortingType);
}
