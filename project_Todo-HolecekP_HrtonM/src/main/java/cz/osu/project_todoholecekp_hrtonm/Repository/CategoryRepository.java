package cz.osu.project_todoholecekp_hrtonm.Repository;

import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //Method for getting a category by a title
    List<Category> findAllByTitleContainsIgnoreCase(String title);
    //Method for getting a list of categories ordered alphabetically by title ascending
    List<Category> findByOrderByTitleAsc();
    //Method for getting a list of categories ordered alphabetically by title descending
    List<Category> findByOrderByTitleDesc();
    //Method for getting a list of categories ordered by the amount of tasks a category contains ascending
    @Query("SELECT t FROM Category t ORDER BY SIZE(t.tasks) ASC")
    List<Category> findAllByOrderByTasksSizeAsc();
    //Method for getting a list of categories ordered by the amount of tasks a category contains descending
    @Query("SELECT t FROM Category t ORDER BY SIZE(t.tasks) DESC ")
    List<Category> findAllByOrderByTasksSizeDesc();
}
