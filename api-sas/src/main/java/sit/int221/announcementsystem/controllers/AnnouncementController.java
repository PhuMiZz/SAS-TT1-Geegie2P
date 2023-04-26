package sit.int221.announcementsystem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.announcementsystem.dtos.AnnouncementsViewDto;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.services.AnnouncementService;
import sit.int221.announcementsystem.utils.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
public class AnnouncementController {
    @Autowired
    private AnnouncementService service;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/announcements")
    public List<AnnouncementsViewDto> getAnnouncements(){
        List<Announcement> announcements = service.getAnnouncements();
        return listMapper.getInstance().mapList(announcements, AnnouncementsViewDto.class, modelMapper);
    }

    @GetMapping("/announcements/{id}")
    public Announcement getAnnouncementDetail(@PathVariable Integer id){
        return service.getAnnouncementDetail(id);
    }
}
