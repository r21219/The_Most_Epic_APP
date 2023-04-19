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

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Todo> todo;

    public User() {
        todo = new ArrayList<>();
    }

    public User(String name, String password, List<Todo> todo) {
        this.name = name;
        this.password = password;
        this.todo = todo;
    }
}
