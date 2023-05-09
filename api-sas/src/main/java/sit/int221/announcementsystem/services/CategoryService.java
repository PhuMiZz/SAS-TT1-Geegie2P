package sit.int221.announcementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.exceptions.BadRequestException;
import sit.int221.announcementsystem.exceptions.ItemNotFoundException;
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

    public Category createCategory(Category category){
        if (category.getCategoryName() == null || category.getCategoryName().isBlank()) throw new BadRequestException("Must insert category name!!");
        return repository.saveAndFlush(category);
    }

    public Category getCategoryById(Integer categoryId){
        return repository.findById(categoryId).orElseThrow(
                () -> new ItemNotFoundException("Category id: " + categoryId + " does not exist!.")
        );
    }
}
