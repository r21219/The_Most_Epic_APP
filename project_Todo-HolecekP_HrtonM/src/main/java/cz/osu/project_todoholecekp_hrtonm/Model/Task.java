package cz.osu.project_todoholecekp_hrtonm.Model;


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

    public Task() {
    }

    public Task(long id, boolean isComplete, String description) {
        this.id = id;
        this.isComplete = isComplete;
        this.description = description;
    }
}
