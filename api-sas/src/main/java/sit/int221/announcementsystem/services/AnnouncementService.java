package sit.int221.announcementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.exceptions.ItemNotFoundException;
import sit.int221.announcementsystem.repositories.AnnouncementRepository;

import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository repository;
    public List<Announcement> getAnnouncements(){
        return repository.findAll();
    }

    public Announcement getAnnouncementDetail(int announcementId){
        return repository.findById(announcementId).orElseThrow(
                () -> new ItemNotFoundException("Announcement ID: " + announcementId + " do not exist!.")
        );
    }
}
