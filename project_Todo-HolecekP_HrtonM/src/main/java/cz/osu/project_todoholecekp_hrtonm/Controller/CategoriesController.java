package cz.osu.project_todoholecekp_hrtonm.Controller;

import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import cz.osu.project_todoholecekp_hrtonm.Model.SortingType;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/categories{id}")
    public Category get(@PathVariable("id") long id) {
        return categoryService.get(id);
    }
    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.getAll();
    }
    @GetMapping("/categories/search/{name}")
    public Category search(@PathVariable("name") String name) {
        return categoryService.search(name);
    }
    @PutMapping("/categories")
    public void update(@Valid @RequestBody Category Category) throws Exception {
        categoryService.update(Category);
    }
    @DeleteMapping("/categories{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        categoryService.delete(id);
    }
    @PostMapping("/categories/add/{id}")
    public Category addTask(@PathVariable("id") long categoryId,@Valid @RequestBody Task newTask) {
        return categoryService.addTask(categoryId, newTask);
    }
    @DeleteMapping("/categories/del/{id}")
    public void deleteTask(@PathVariable("id") long taskId) {
        categoryService.deleteTask(taskId);
    }
    @GetMapping("/categories/sort{value}")
    public List<Category> sortedGet(@PathVariable("value") int sortingValue) {
        return categoryService.sortedGet(SortingType.values()[sortingValue]);
    }
}
