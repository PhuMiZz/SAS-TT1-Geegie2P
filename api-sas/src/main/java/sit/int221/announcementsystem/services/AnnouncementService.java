package sit.int221.announcementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.announcementsystem.entities.Announcement;
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
        return repository.findById(announcementId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Announcement ID: " + announcementId + "DOES NOT EXIST!!!"
        ));
    }
}
