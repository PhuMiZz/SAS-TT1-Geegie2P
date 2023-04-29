package sit.int221.announcementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
    List<Announcement> findAllByOrderByPublishDateDescCloseDateDesc();
    public List<Announcement> getAnnouncementsByCategory(Category category);
}
