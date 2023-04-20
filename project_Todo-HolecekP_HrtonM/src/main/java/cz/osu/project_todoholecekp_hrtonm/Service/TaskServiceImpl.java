package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Exception.RecordNotFoundException;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Repository.TaskRepository;
import cz.osu.project_todoholecekp_hrtonm.Repository.CategoryRepository;

import java.util.List;

public class TaskServiceImpl implements TaskService{
    TaskRepository taskRepository;
    CategoryRepository categoryRepository;

    public TaskServiceImpl(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Task create(Task newTask) {
        Category category = categoryRepository.findById(newTask.getCategory().getId()).orElseThrow(() ->
                new RecordNotFoundException("Category not found."));

        newTask.setCategory(category);
        return taskRepository.save(newTask);
    }

    @Override
    public Task get(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task does not exist"));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task search(String description) {
        return taskRepository.findAllByDescriptionContainsIgnoreCase(description);
    }

    @Override
    public void update(Task task){
        Task dbTask = get(task.getId());
        if (dbTask != null){
            dbTask.setComplete(task.isComplete());
            dbTask.setDescription(task.getDescription());
            dbTask.setDeadLine(task.getDeadLine());
            dbTask.setCategory(task.getCategory());
            taskRepository.save(dbTask);
        } else {
            throw new RecordNotFoundException("Task does not exist");
        }
    }

    @Override
    public void delete(long id){
        boolean exists = taskRepository.existsById(id);
        if (exists){
            taskRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Task does not exist");
        }
    }
}
