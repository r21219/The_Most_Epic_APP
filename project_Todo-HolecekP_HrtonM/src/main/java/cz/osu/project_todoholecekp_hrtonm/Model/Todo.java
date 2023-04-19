package cz.osu.project_todoholecekp_hrtonm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
//Todo requires adding methods and attributes
@Entity
@Table(name = "todos" )
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String Title;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Todo (){
        tasks = new ArrayList<>();
    }
    public Todo(long id, String title, List<Task> tasks) {
        this.id = id;
        Title = title;
        this.tasks = tasks;
    }
}

