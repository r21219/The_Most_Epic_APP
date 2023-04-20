package cz.osu.project_todoholecekp_hrtonm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
//Todo requires adding methods and attributes
@Entity
@Table(name = "users")
public class User {
    @Id
    @NotNull
    private String name;

    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user ", fetch = FetchType.EAGER)
    private List<Todo> todos;

    public User() {
        todos = new ArrayList<>();
    }

    public User(String name, String password, List<Todo> todo) {
        this.name = name;
        this.password = password;
        this.todos = todo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getTodo() {
        return todos;
    }

    public void setTodo(List<Todo> todo) {
        this.todos = todo;
    }

    public void addTodo(Todo todo){
        todo.setUser(this);
        todos.add(todo);
    }

    public void removeTodo(Todo todo){
        todos.remove(todo);
    }
}
