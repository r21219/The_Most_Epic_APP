package cz.osu.project_todoholecekp_hrtonm.Repository;

import cz.osu.project_todoholecekp_hrtonm.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Method for finding categories by user name
    List<Category> findByUser_Name(String userName);

    // Method for finding categories by title containing a specific value and user name
    List<Category> findAllByTitleContainsIgnoreCaseAndUser_Name(String title, String userName);

    // Method for getting a list of categories ordered alphabetically by title ascending for a specific user
    List<Category> findByUser_NameOrderByTitleAsc(String userName);

    // Method for getting a list of categories ordered alphabetically by title descending for a specific user
    List<Category> findByUser_NameOrderByTitleDesc(String userName);

    // Method for getting a list of categories ordered by the amount of tasks a category contains ascending for a specific user
    @Query("SELECT c FROM Category c WHERE c.user.name = :userName ORDER BY SIZE(c.tasks) ASC")
    List<Category> findAllByOrderByTasksSizeAsc(String userName);

    // Method for getting a list of categories ordered by the amount of tasks a category contains descending for a specific user
    @Query("SELECT c FROM Category c WHERE c.user.name = :userName ORDER BY SIZE(c.tasks) DESC")
    List<Category> findAllByOrderByTasksSizeDesc(String userName);
}
