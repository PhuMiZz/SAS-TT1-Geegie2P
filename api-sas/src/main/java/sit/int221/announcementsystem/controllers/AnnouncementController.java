package sit.int221.announcementsystem.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.announcementsystem.dtos.*;
import org.springframework.web.bind.annotation.*;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.exceptions.BadRequestException;
import sit.int221.announcementsystem.services.AnnouncementService;
import sit.int221.announcementsystem.utils.ListMapper;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/announcements")
@CrossOrigin("${CORS_ORIGIN:http://localhost:5173}")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper = ListMapper.getInstance();
    @GetMapping("")
    public List<AnnouncementsViewDto> getAnnouncements(
            @RequestParam(value = "mode", defaultValue = "admin") String mode){
        List<Announcement> announcements = announcementService.getAnnouncementsByAdmin(mode);
        return listMapper.mapList(announcements, AnnouncementsViewDto.class, modelMapper);
    }

    @GetMapping("/{id}")
    public AnnouncementDetailDto getAnnouncementDetail(@PathVariable Integer id) {
        return modelMapper.map(announcementService.getAnnouncementDetail(id), AnnouncementDetailDto.class);
    }

    @PostMapping("")
    public AnnouncementCreateUpdateViewDto createAnnouncement(@RequestBody @Valid AnnouncementCreateUpdateDto newAnnouncement) {
        return announcementService.createAnnouncement(newAnnouncement);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
            announcementService.DeleteAnnouncement(id);
    }
    @PutMapping("/{id}")
    public AnnouncementCreateUpdateViewDto updateAnnouncement(@PathVariable Integer id,@RequestBody @Valid AnnouncementCreateUpdateDto updateAnnouncement){
            AnnouncementCreateUpdateDto oldAnnouncement = modelMapper.map(announcementService.getAnnouncementDetail(id),AnnouncementCreateUpdateDto.class);
            return announcementService.updateAnnouncement(updateAnnouncement, oldAnnouncement);
    }

    @GetMapping("/category/{categoryId}")
    public List<AnnouncementsViewDto> getAllAnnouncementByCategory(@PathVariable Integer categoryId){
        return listMapper.mapList(announcementService.getAnnouncementByCategory(categoryId), AnnouncementsViewDto.class, modelMapper);
    }

    @GetMapping("/pages")
    public PageDto<AnnouncementsViewDto> getAnnouncementsPages(
            @RequestParam(value = "mode", defaultValue = "admin") String mode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "category", required = false) Integer categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Announcement> announcements = announcementService.getAnnouncementsByModeAndCategory(mode, categoryId, pageable);
        return listMapper.toPageDTO(announcements, AnnouncementsViewDto.class, modelMapper);
    }
}
