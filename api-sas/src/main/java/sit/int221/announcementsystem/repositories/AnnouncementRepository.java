package sit.int221.announcementsystem.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;


import java.time.ZonedDateTime;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
    List<Announcement> findAllByOrderByPublishDateDescCloseDateDesc();
    List<Announcement> getAnnouncementsByCategory(Category category);
    Page<Announcement> findAllByAnnouncementDisplayAndCloseDateAfter(Announcement.DisplayStatus displayStatus, ZonedDateTime closeDate, Pageable pageable);
    Page<Announcement> findAllByAnnouncementDisplayAndCloseDateBefore(Announcement.DisplayStatus displayStatus, ZonedDateTime closeDate, Pageable pageable);
    Page<Announcement> findAllByOrderByPublishDateDescCloseDateDesc(Pageable pageable);


    public List<Announcement> getAnnouncementsByCategory(Category category);
}
