package sit.int221.announcementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.repositories.AnnouncementRepository;
import sit.int221.announcementsystem.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    public List<Category> getCategories(){
        return repository.findAll();
    }

    public Integer FindCategoryByName(String categoryName){
        return repository.getCategoryByCategoryName(categoryName).getId();
    }
}
