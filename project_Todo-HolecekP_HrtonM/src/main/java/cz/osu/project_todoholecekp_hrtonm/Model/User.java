package cz.osu.project_todoholecekp_hrtonm.Model;

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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Category> categories;

    public User() {
        categories = new ArrayList<>();
    }

    public User(String name, String password, List<Category> category) {
        this.name = name;
        this.password = password;
        this.categories = category;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> category) {
        this.categories = category;
    }

    public void addCategory(Category category){
        category.setUser(this);
        categories.add(category);
    }

    public void removeCategory(Category category){
        categories.remove(category);
    }
}
