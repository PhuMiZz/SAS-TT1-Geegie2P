package sit.int221.announcementsystem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.announcementsystem.dtos.*;

import org.springframework.web.bind.annotation.*;

import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.exceptions.BadRequestException;

import sit.int221.announcementsystem.services.AnnouncementService;
import sit.int221.announcementsystem.services.CategoryService;
import sit.int221.announcementsystem.utils.ListMapper;

import java.util.List;


@RestController
@RequestMapping("/api/announcements")
@CrossOrigin("${CORS_ORIGIN:http://localhost:5173}")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper = ListMapper.getInstance();
    @GetMapping("")
    public List<AnnouncementsViewDto> getAnnouncements() {
        return announcementService.getAnnouncementsViewDtos();
    }

    @GetMapping("/{id}")
    public AnnouncementDetailDto getAnnouncementDetail(@PathVariable String id) {
        try {
            int announcementId = Integer.parseInt(id);
            return modelMapper.map(announcementService.getAnnouncementDetail(announcementId), AnnouncementDetailDto.class);
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid announcement ID: " + id);
        }
    }

    @PostMapping("")
    public AnnouncementCreateUpdateViewDto createAnnouncement(@RequestBody AnnouncementCreateUpdateDto newAnnouncement) {
        try {
            return announcementService.createAnnouncement(newAnnouncement);
        } catch (DataIntegrityViolationException e){
            throw new BadRequestException("Data incorrect");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
            announcementService.DeleteAnnouncement(id);
    }
    @PutMapping("/{id}")
    public AnnouncementCreateUpdateViewDto updateAnnouncement(@PathVariable Integer id,@RequestBody AnnouncementCreateUpdateDto updateAnnouncement){
        try {
            AnnouncementCreateUpdateDto oldAnnouncement = modelMapper.map(announcementService.getAnnouncementDetail(id),AnnouncementCreateUpdateDto.class);
            return announcementService.updateAnnouncement(updateAnnouncement, oldAnnouncement);
        } catch (DataIntegrityViolationException e){
            throw new BadRequestException("Announcement not found");
        }
    }

    @GetMapping("/category/{categoryId}")
    public List<AnnouncementsViewDto> getAllAnnouncementByCategory(@PathVariable Integer categoryId){
        return ListMapper.getInstance().mapList(announcementService.getAnnouncementByCategory(categoryId), AnnouncementsViewDto.class, modelMapper);
    }


    @GetMapping("/pages")
    public PageDto<AnnouncementsViewDto> getAnnouncementsPages(
            @RequestParam(value = "mode", defaultValue = "admin") String mode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Announcement> announcements = switch (mode.toLowerCase()) {
            case "admin" -> announcementService.getAdminAnnouncements(pageable);
            case "closed" -> announcementService.getClosedAnnouncements(pageable);
            default -> announcementService.getActiveAnnouncements(pageable);
        };

        return listMapper.toPageDTO(announcements, AnnouncementsViewDto.class, modelMapper);
    }

}
