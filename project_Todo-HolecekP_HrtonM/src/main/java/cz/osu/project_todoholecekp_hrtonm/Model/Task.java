package cz.osu.project_todoholecekp_hrtonm.Model;

import java.time.LocalDate;
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
    private LocalDate deadLine;
    private String description;
    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;
    public Task() {
    }

    public Task(long id, boolean isComplete, String description, Category category, LocalDate deadLine) {
        this.id = id;
        this.isComplete = false;
        this.description = description;
        this.category = category;
        this.deadLine = deadLine;
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

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
