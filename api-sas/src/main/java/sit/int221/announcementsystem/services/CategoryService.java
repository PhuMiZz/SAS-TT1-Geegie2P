package sit.int221.announcementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private CategoryRepository categoryRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category){
        if (category.getCategoryName() == null || category.getCategoryName().isBlank()) throw new BadRequestException("Must insert category name!!");
        return categoryRepository.saveAndFlush(category);
    }

    public Category getCategoryById(Integer categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new ItemNotFoundException("Category id: " + categoryId + " does not exist!.")
        );
    }
//    public Page<Announcement> getPageAnnouncementsByCategory(int categoryId, Pageable pageable) {
//        Category category = categoryRepository.findById(categoryId).orElseThrow(
//                () -> new ItemNotFoundException("Category ID: " + categoryId + " does not exist!.")
//        );
//        return announcementRepository.findByCategory(category, pageable);
//    }

}
