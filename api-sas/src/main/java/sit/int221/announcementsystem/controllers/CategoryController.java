package sit.int221.announcementsystem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.announcementsystem.dtos.AnnouncementsViewDto;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.services.AnnouncementService;
import sit.int221.announcementsystem.services.CategoryService;
import sit.int221.announcementsystem.utils.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("${CORS_ORIGIN:http://localhost:5173}")
public class CategoryController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryById(id) ;
    }
//    @PostMapping("")
//    public Category createCategory(@RequestBody Category newCategory){
//        return categoryService.createCategory(newCategory);
//    }
}
