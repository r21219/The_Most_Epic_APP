package cz.osu.project_todoholecekp_hrtonm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;
//Todo requires adding methods and attributes
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String Title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "todo", fetch = FetchType.EAGER)
    @JoinColumn (name = "task_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Task> tasks;
}
