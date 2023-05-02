package sit.int221.announcementsystem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.announcementsystem.dtos.AnnouncementCreateDto;
import sit.int221.announcementsystem.dtos.AnnouncementCreateViewDto;
import sit.int221.announcementsystem.dtos.AnnouncementDetailDto;

import org.springframework.web.bind.annotation.*;

import sit.int221.announcementsystem.dtos.AnnouncementsViewDto;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.exceptions.BadRequest;
import sit.int221.announcementsystem.exceptions.InvalidAnnouncementIdException;
import sit.int221.announcementsystem.services.AnnouncementService;
import sit.int221.announcementsystem.services.CategoryService;
import sit.int221.announcementsystem.utils.ListMapper;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/announcements")
    public List<AnnouncementsViewDto> getAnnouncements(){
        List<Announcement> announcements = announcementService.getAnnouncements();
        return listMapper.getInstance().mapList(announcements, AnnouncementsViewDto.class, modelMapper);
    }

    @GetMapping("/announcements/{id}")
    public AnnouncementDetailDto getAnnouncementDetail(@PathVariable String id) {
        try {
            int announcementId = Integer.parseInt(id);
            return modelMapper.map(announcementService.getAnnouncementDetail(announcementId), AnnouncementDetailDto.class);
        } catch (NumberFormatException ex) {
            throw new InvalidAnnouncementIdException("Invalid announcement ID: " + id);
        }
    }
//    @GetMapping("/announcements/{id}")
//    public AnnouncementCreateDto getAnnouncementById(@PathVariable String id) {
//        try {
//            int announcementId = Integer.parseInt(id);
//            return modelMapper.map(announcementService.getAnnouncementDetail(announcementId), AnnouncementCreateDto.class);
//        }  catch (NumberFormatException ex) {
//            throw new InvalidAnnouncementIdException("Invalid announcement ID: " + id);
//        }
//
//    }

    @PostMapping("/announcements")
    public AnnouncementCreateViewDto createAnnouncement(@RequestBody AnnouncementCreateDto newAnnouncement){
        try {
            return announcementService.createAnnouncement(newAnnouncement);
        } catch (Exception e) {
            throw new BadRequest("Error creating announcement");
        }
        
    }

    // Category
    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/categories/find/{categoryName}")
    public Integer findCategoryIdByName (@PathVariable String categoryName){
        return categoryService.FindCategoryByName(categoryName);
    }

}
