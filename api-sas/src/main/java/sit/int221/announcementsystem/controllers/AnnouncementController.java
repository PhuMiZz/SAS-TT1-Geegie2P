package sit.int221.announcementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.services.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("api/announcements")
public class AnnouncementController {
    @Autowired
    private AnnouncementService service;
    @GetMapping("")
    public List<Announcement> getAnnouncements(){
        return service.getAnnouncements();
    }

    @GetMapping("/{announcementId}/detail")
    public Announcement getAnnouncementDetail(@PathVariable Integer announcementId){
        return service.getAnnouncementDetail(announcementId);
    }
}
