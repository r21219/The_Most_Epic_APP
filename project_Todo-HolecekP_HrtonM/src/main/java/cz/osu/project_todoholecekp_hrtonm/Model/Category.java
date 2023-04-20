package cz.osu.project_todoholecekp_hrtonm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
//Class for a table named Categories.
//Categories contain multiple Tasks and one User for many Categories
@Entity
@Table(name = "categories" )
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "category", fetch = FetchType.EAGER)
    private List<Task> tasks;
    @ManyToOne
    @JoinColumn(name="user_name")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
    public Category(){
        tasks = new ArrayList<>();
    }
    public Category(long id, String title, List<Task> tasks, User user) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addTask(Task task){
        task.setCategory(this);
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }
}

