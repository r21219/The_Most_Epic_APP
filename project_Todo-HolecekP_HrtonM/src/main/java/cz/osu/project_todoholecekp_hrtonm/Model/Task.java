package cz.osu.project_todoholecekp_hrtonm.Model;

import jakarta.persistence.*;
//Todo requires adding methods and attributes
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
