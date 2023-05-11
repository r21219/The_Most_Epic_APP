package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Exception.RecordNotFoundException;
import cz.osu.project_todoholecekp_hrtonm.Exception.WrongInputFormatException;
import cz.osu.project_todoholecekp_hrtonm.Model.SortingType;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Repository.TaskRepository;
import cz.osu.project_todoholecekp_hrtonm.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
    public List<Task> search(String title) {
        return taskRepository.findAllByTitleContainsIgnoreCase(title);
    }

    @Override
    public Task update(Task task){
        Task dbTask = get(task.getId());
        if (dbTask != null){
            dbTask.setComplete(task.isComplete());
            dbTask.setTitle(task.getTitle());
            dbTask.setDeadLine(task.getDeadLine());
            return taskRepository.save(dbTask);
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

    @Override
    public List<Task> sortedGet(long categoryId,SortingType sortingType) {
        List<Task> tasks;

        switch (sortingType){
            case TASK_TITLE_ASC -> tasks = taskRepository.findByCategory_IdOrderByTitleAsc(categoryId);

            case TASK_TITLE_DESC -> tasks = taskRepository.findByCategory_IdOrderByTitleDesc(categoryId);

            case TASK_DEADLINE_ASC -> tasks = taskRepository.findByCategory_IdOrderByDeadlineAsc(categoryId);

            case TASK_DEADLINE_DESC -> tasks = taskRepository.findByCategory_IdOrderByDeadlineDesc(categoryId);

            case TASK_COMPLETED_ASC -> tasks = taskRepository.findByCategory_IdOrderByCompleteAsc(categoryId);

            case TASK_COMPLETED_DESC -> tasks = taskRepository.findByCategory_IdOrderByCompleteDesc(categoryId);

            default -> throw new WrongInputFormatException("Wrong order type put in");
        }
        return tasks;
    }
}
