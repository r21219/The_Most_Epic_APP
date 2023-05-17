package cz.osu.project_todoholecekp_hrtonm.Controller;

import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Model.SortingType;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @PostMapping("/categories")
    public Category create(@Valid @RequestBody Category newCategory) {
        return categoryService.create(newCategory);
    }
    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") long id) {
        return categoryService.get(id);
    }
    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.getAll();
    }
    @GetMapping("/categories/user/{userName}")
    public List<Category> getByUser(@PathVariable("userName") String userName) {
        return categoryService.getByUser(userName);
    }
    @GetMapping("/categories/search/{title}")
    public List<Category> search(@PathVariable("title") String title) {
        return categoryService.search(title);
    }
    @PutMapping("/categories")
    public Category update(@Valid @RequestBody Category Category) throws Exception {
        return categoryService.update(Category);
    }
    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        categoryService.delete(id);
    }
    @PostMapping("/categories/add/{id}")
    public Category addTask(@PathVariable("id") long categoryId,@Valid @RequestBody Task newTask) {
        return categoryService.addTask(categoryId, newTask);
    }
    @DeleteMapping("/categories/del/{id}")
    public Category deleteTask(@PathVariable("id") long taskId) {
        return categoryService.deleteTask(taskId);
    }
    @GetMapping("/categories/sort/{value}")
    public List<Category> sortedGet(@PathVariable("value") int sortingValue) {
        return categoryService.sortedGet(SortingType.values()[sortingValue]);
    }
}
