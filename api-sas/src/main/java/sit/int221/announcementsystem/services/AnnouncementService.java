package sit.int221.announcementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.exceptions.ItemNotFoundException;
import sit.int221.announcementsystem.repositories.AnnouncementRepository;
import sit.int221.announcementsystem.repositories.CategoryRepository;

import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Announcement> getAnnouncements(){
        return announcementRepository.findAll(Sort.by(Sort.Direction.DESC, "publishDate","closeDate"));
    }

    public Announcement getAnnouncementDetail(int announcementId){
        return announcementRepository.findById(announcementId).orElseThrow(
                () -> new ItemNotFoundException("Announcement ID: " + announcementId + " does not exist!.")
        );
    }

    public List<Announcement> getAnnouncementByCategory(int categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ItemNotFoundException("Category ID: " + categoryId + " does not exist!.")
        );
        return announcementRepository.getAnnouncementsByCategory(category);
    }
}
