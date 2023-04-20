package cz.osu.project_todoholecekp_hrtonm.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

//Todo requires adding methods and attributes
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private boolean isComplete;

    private String description;
    @ManyToOne
    @JoinColumn(name="todo_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Todo todo;
    public Task() {
    }

    public Task(long id, boolean isComplete, String description, Todo todo) {
        this.id = id;
        this.isComplete = false;
        this.description = description;
        this.todo = todo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}
