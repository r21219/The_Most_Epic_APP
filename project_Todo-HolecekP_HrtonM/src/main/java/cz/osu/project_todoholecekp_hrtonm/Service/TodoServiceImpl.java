package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Exception.RecordNotFoundException;
import cz.osu.project_todoholecekp_hrtonm.Exception.WrongInputFormatException;
import cz.osu.project_todoholecekp_hrtonm.Model.Task;
import cz.osu.project_todoholecekp_hrtonm.Model.Todo;
import cz.osu.project_todoholecekp_hrtonm.Model.User;
import cz.osu.project_todoholecekp_hrtonm.Repository.TaskRepository;
import cz.osu.project_todoholecekp_hrtonm.Repository.TodoRepository;
import cz.osu.project_todoholecekp_hrtonm.Repository.UserRepository;

import java.util.List;

public class TodoServiceImpl implements TodoService{
    TodoRepository todoRepository;
    UserRepository userRepository;
    TaskRepository taskRepository;
    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Todo create(Todo newTodo) {
        User user = userRepository.findById(newTodo.getUser().getName()).orElseThrow(()
                -> new RecordNotFoundException("User not found"));
        newTodo.setUser(user);
        return todoRepository.save(newTodo);
    }

    @Override
    public Todo get(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Todo does not exist"));
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo search(String title) {
        return todoRepository.findAllByTitleContainsIgnoreCase(title);
    }

    @Override
    public void update(Todo Todo){
        Todo dbTodo = get(Todo.getId());
        if (dbTodo != null){
            dbTodo.setTitle(dbTodo.getTitle());
            todoRepository.save(dbTodo);
        } else{
            throw new RecordNotFoundException("Todo does not exist");
        }
    }

    @Override
    public void delete(long id){
        boolean exists = todoRepository.existsById(id);
        if (exists){
            todoRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Todo does not exist");
        }
    }

    @Override
    public Todo addTask(long todoId, Task newTask) {
        if (newTask == null){
            throw new WrongInputFormatException("New task cannot be null");
        }
        Todo todo = todoRepository.findById(todoId).orElseThrow(()
                -> new RecordNotFoundException("Todo does not exist"));
        newTask.setTodo(todo);
        taskRepository.save(newTask);
        todo.addTask(newTask);
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTask(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new RecordNotFoundException("Task does not exist"));
        Todo todo = todoRepository.findById(task.getTodo().getId())
                .orElseThrow(() -> new RecordNotFoundException("Todo not found"));
        todo.removeTask(task);
        taskRepository.deleteById(taskId);
        todoRepository.save(todo);
    }
}
