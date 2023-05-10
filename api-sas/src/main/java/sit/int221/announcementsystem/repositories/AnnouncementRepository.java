package sit.int221.announcementsystem.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;


import java.time.ZonedDateTime;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
    List<Announcement> findAllByOrderByPublishDateDescCloseDateDesc();
    List<Announcement> findAllByAnnouncementDisplayAndCloseDateAfter(Announcement.DisplayStatus displayStatus, ZonedDateTime closeDate);
    List<Announcement> findAllByAnnouncementDisplayAndCloseDateBefore(Announcement.DisplayStatus displayStatus, ZonedDateTime closeDate);
    List<Announcement> getAnnouncementsByCategory(Category category);
    Page<Announcement> findAllByOrderByPublishDateDescCloseDateDesc(Pageable pageable);
    @Query("SELECT a FROM Announcement a WHERE a.category.id = :categoryId ORDER BY a.publishDate DESC, a.closeDate DESC")
    Page<Announcement> findByCategoryOrderByPublishDateDescCloseDateDesc(@Param("categoryId") Integer categoryId, Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = 'Y' AND a.closeDate > CURRENT_TIMESTAMP")
    Page<Announcement> findActiveAnnouncements(Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.category.id = :categoryId AND a.announcementDisplay = 'Y' AND a.closeDate > CURRENT_TIMESTAMP")
    Page<Announcement> findActiveAnnouncementsByCategory(@Param("categoryId") Integer categoryId, Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = 'Y' AND a.closeDate <= CURRENT_TIMESTAMP")
    Page<Announcement> findClosedAnnouncements(Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.category.id = :categoryId AND a.announcementDisplay = 'Y' AND a.closeDate <= CURRENT_TIMESTAMP")
    Page<Announcement> findClosedAnnouncementsByCategory(@Param("categoryId") Integer categoryId, Pageable pageable);
}
