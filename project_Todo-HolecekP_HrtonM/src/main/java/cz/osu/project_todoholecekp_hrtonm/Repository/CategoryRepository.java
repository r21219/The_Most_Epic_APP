package cz.osu.project_todoholecekp_hrtonm.Repository;

import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findAllByTitleContainsIgnoreCase(String title);
    List<Category> findByOrderByTitleAsc();
    List<Category> findByOrderByTitleDesc();
    @Query("SELECT t FROM Category t ORDER BY SIZE(t.tasks) ASC")
    List<Category> findAllByOrderByTasksSizeAsc();
    @Query("SELECT t FROM Category t ORDER BY SIZE(t.tasks) DESC ")
    List<Category> findAllByOrderByTasksSizeDesc();
}
