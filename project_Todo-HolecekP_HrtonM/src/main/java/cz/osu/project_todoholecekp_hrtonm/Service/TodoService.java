package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Model.Todo;

import java.util.List;

public interface TodoService {
    Todo create(Todo newTodo);
    Todo get(long id);
    List<Todo> getAll();
    Todo search(String name);
    void update(Todo Todo) throws Exception;
    void delete(long id) throws Exception;
}
