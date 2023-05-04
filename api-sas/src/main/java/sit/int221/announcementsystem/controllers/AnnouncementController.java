package sit.int221.announcementsystem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateDto;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateViewDto;
import sit.int221.announcementsystem.dtos.AnnouncementDetailDto;

import org.springframework.web.bind.annotation.*;

import sit.int221.announcementsystem.dtos.AnnouncementsViewDto;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.exceptions.BadRequestException;

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
    private ModelMapper modelMapper;

    @GetMapping("/announcements")
    public List<AnnouncementsViewDto> getAnnouncements() {
        List<Announcement> announcements = announcementService.getAnnouncements();
        return ListMapper.getInstance().mapList(announcements, AnnouncementsViewDto.class, modelMapper);
    }

    @GetMapping("/announcements/{id}")
    public AnnouncementDetailDto getAnnouncementDetail(@PathVariable String id) {
        try {
            int announcementId = Integer.parseInt(id);
            return modelMapper.map(announcementService.getAnnouncementDetail(announcementId), AnnouncementDetailDto.class);
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid announcement ID: " + id);
        }
    }

    @PostMapping("/announcements")
    public AnnouncementCreateUpdateViewDto createAnnouncement(@RequestBody AnnouncementCreateUpdateDto newAnnouncement) {
        return announcementService.createAnnouncement(newAnnouncement);
    }

    @DeleteMapping("/announcements/{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
            announcementService.DeleteAnnouncement(id);
    }
    @PutMapping("/announcements/{id}")
    public AnnouncementCreateUpdateViewDto updateAnnouncement(@PathVariable Integer id,@RequestBody AnnouncementCreateUpdateDto updateAnnouncement){
        try {
            AnnouncementCreateUpdateDto oldAnnouncement = modelMapper.map(announcementService.getAnnouncementDetail(id),AnnouncementCreateUpdateDto.class);
            return announcementService.updateAnnouncement(updateAnnouncement, oldAnnouncement);
        } catch (DataIntegrityViolationException e){
            throw new BadRequestException("Announcement not found");
        }
    }
    // Category
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/categories/find/{categoryName}")
    public Integer findCategoryIdByName(@PathVariable String categoryName) {
            return categoryService.FindCategoryByName(categoryName);
    }


}
