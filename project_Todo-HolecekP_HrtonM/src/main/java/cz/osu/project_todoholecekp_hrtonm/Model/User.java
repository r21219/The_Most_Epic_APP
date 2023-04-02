package cz.osu.project_todoholecekp_hrtonm.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @NotNull
    private String name;

    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user", fetch = FetchType.EAGER)
    @JoinColumn (name = "todo_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Todo> todo;
}
