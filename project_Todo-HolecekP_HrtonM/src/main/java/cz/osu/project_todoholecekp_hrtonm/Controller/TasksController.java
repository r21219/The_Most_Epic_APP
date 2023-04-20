package cz.osu.project_todoholecekp_hrtonm.Controller;

import cz.osu.project_todoholecekp_hrtonm.Model.SortingType;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {
   private final TaskService taskService;

    public TasksController(TaskService taskService){
        this.taskService = taskService;
    }
    @PostMapping("tasks")
    public Task create(@Valid @RequestBody Task newTask) {
        return taskService.create(newTask);
    }
    @GetMapping("tasks{id}")
    public Task get(@PathVariable("id") long id) {
        return taskService.get(id);
    }
    @GetMapping("tasks")
    public List<Task> getAll() {
        return taskService.getAll();
    }
    @GetMapping("tasks/search/{title}")
    public Task search(@PathVariable("title") String title) {
        return taskService.search(title);
    }
    @PutMapping("tasks")
    public void update(@Valid @RequestBody Task task) throws Exception {
        taskService.update(task);
    }
    @DeleteMapping("tasks{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        taskService.delete(id);
    }
    @GetMapping("/tasks/sort{value}")
    public List<Task> sortedGet(@PathVariable("value") int sortingValue) {
        return taskService.sortedGet(SortingType.values()[sortingValue]);
    }
}
