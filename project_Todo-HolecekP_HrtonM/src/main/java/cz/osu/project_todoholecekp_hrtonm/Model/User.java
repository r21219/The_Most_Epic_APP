package cz.osu.project_todoholecekp_hrtonm.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//Class for a table named User.
//User contains multiple Categories per user
@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @NotNull
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
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
    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
