package sit.int221.announcementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.announcementsystem.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    public Category getCategoryByCategoryName(String categoryName);
}
