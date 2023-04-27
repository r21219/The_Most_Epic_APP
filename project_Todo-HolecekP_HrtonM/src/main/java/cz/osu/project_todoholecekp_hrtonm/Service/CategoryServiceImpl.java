package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Exception.RecordNotFoundException;
import cz.osu.project_todoholecekp_hrtonm.Exception.WrongInputFormatException;
import cz.osu.project_todoholecekp_hrtonm.Model.SortingType;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Model.User;
import cz.osu.project_todoholecekp_hrtonm.Repository.TaskRepository;
import cz.osu.project_todoholecekp_hrtonm.Repository.CategoryRepository;
import cz.osu.project_todoholecekp_hrtonm.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    UserRepository userRepository;
    TaskRepository taskRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Category create(Category newCategory) {
        User user = userRepository.findById(newCategory.getUser().getName()).orElseThrow(()
                -> new RecordNotFoundException("User not found"));
        newCategory.setUser(user);
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category get(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Category does not exist"));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> search(String title) {
        return categoryRepository.findAllByTitleContainsIgnoreCase(title);
    }

    @Override
    public void update(Category category){
        Category dbCategory = get(category.getId());
        if (dbCategory != null){
            dbCategory.setTitle(category.getTitle());
            categoryRepository.save(dbCategory);
        } else{
            throw new RecordNotFoundException("Category does not exist");
        }
    }

    @Override
    public void delete(long id){
        boolean exists = categoryRepository.existsById(id);
        if (exists){
            categoryRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Category does not exist");
        }
    }

    @Override
    public Category addTask(long categoryId, Task newTask) {
        if (newTask == null){
            throw new WrongInputFormatException("New task cannot be null");
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(()
                -> new RecordNotFoundException("Category does not exist"));
        newTask.setCategory(category);
        taskRepository.save(newTask);
        category.addTask(newTask);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteTask(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new RecordNotFoundException("Task does not exist"));
        Category category = categoryRepository.findById(task.getCategory().getId())
                .orElseThrow(() -> new RecordNotFoundException("Category not found"));
        category.removeTask(task);
        taskRepository.deleteById(taskId);
        categoryRepository.save(category);
    }

    @Override
    public List<Category> sortedGet(SortingType sortingType) {
        List<Category> categories;

        switch (sortingType){
            case CATEGORY_TITLE_ASC -> categories = categoryRepository.findByOrderByTitleAsc();

            case CATEGORY_TITLE_DESC -> categories = categoryRepository.findByOrderByTitleDesc();

            case CATEGORY_TASKS_COUNT_ASC -> categories = categoryRepository.findAllByOrderByTasksSizeAsc();

            case CATEGORY_TASKS_COUNT_DESC -> categories = categoryRepository.findAllByOrderByTasksSizeDesc();

            default -> throw new WrongInputFormatException("Wrong order type put in");
        }
        return categories;
    }
}
