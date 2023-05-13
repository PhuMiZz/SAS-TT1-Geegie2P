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
    List<Announcement> getAnnouncementsByCategory(Category category);
    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = :displayStatus AND (a.closeDate IS NULL OR a.closeDate > :currentDate) AND (a.publishDate IS NULL OR a.publishDate <= :currentDate) ORDER BY a.id DESC")
    List<Announcement> findActiveAnnouncementsWithDisplayStatus(@Param("displayStatus") Announcement.DisplayStatus displayStatus, @Param("currentDate") ZonedDateTime currentDate);

//    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = :displayStatus AND (a.closeDate IS NOT NULL AND a.closeDate <= CURRENT_TIMESTAMP) ORDER BY a.publishDate DESC, a.closeDate DESC")
//    List<Announcement> findClosedAnnouncementsWithDisplayStatus(@Param("displayStatus") Announcement.DisplayStatus displayStatus);
    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = :displayStatus AND a.closeDate IS NOT NULL AND a.closeDate <= :currentDate ORDER BY a.id DESC ")
    List<Announcement> findClosedAnnouncementsWithDisplayStatus(@Param("displayStatus") Announcement.DisplayStatus displayStatus, @Param("currentDate") ZonedDateTime currentDate);

    Page<Announcement> findAllByOrderById(Pageable pageable);
    @Query("SELECT a FROM Announcement a WHERE a.category.id = :categoryId ORDER BY a.publishDate DESC, a.closeDate DESC")
    Page<Announcement> findByCategoryOrderByPublishDateDescCloseDateDesc(@Param("categoryId") Integer categoryId, Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = 'Y' AND (a.closeDate IS NULL OR a.closeDate > :currentDate) AND (a.publishDate IS NULL OR a.publishDate <= :currentDate ) ORDER BY a.id DESC ")
    Page<Announcement> findActiveAnnouncements(Pageable pageable, ZonedDateTime currentDate);

    @Query("SELECT a FROM Announcement a WHERE a.category.id = :categoryId AND a.announcementDisplay = 'Y' AND (a.closeDate IS NULL OR a.closeDate > :currentDate) AND (a.publishDate IS NULL OR a.publishDate <= :currentDate) ORDER BY a.id DESC ")
    Page<Announcement> findActiveAnnouncementsByCategory(@Param("categoryId") Integer categoryId, Pageable pageable, ZonedDateTime currentDate);

    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = 'Y' AND a.closeDate IS NOT NULL AND a.closeDate <= :currentDate ORDER BY a.id DESC ")
    Page<Announcement> findClosedAnnouncements(Pageable pageable, ZonedDateTime currentDate);

    @Query("SELECT a FROM Announcement a WHERE a.category.id = :categoryId AND a.announcementDisplay = 'Y' AND a.closeDate IS NOT NULL AND a.closeDate <= :currentDate ORDER BY a.id DESC ")
    Page<Announcement> findClosedAnnouncementsByCategory(@Param("categoryId") Integer categoryId, Pageable pageable, ZonedDateTime currentDate);
//    Page<Announcement> findByCategoryOrderByPublishDateDescCloseDateDesc(Category category, Pageable pageable);
//
//    Page<Announcement> findByAnnouncementDisplayAndCloseDateNullOrCloseDateAfterOrderByPublishDateDesc(Announcement.DisplayStatus displayStatus, ZonedDateTime closeDate, Pageable pageable);
//
//    Page<Announcement> findByCategoryAndAnnouncementDisplayAndCloseDateIsNullOrderByPublishDateDesc(Category category, Announcement.DisplayStatus displayStatus, Pageable pageable);
//
//    Page<Announcement> findByAnnouncementDisplayAndCloseDateIsNotNullAndCloseDateBeforeOrderByPublishDateDesc(Announcement.DisplayStatus displayStatus, ZonedDateTime closeDate, Pageable pageable);
//
//    Page<Announcement> findByCategoryAndAnnouncementDisplayAndCloseDateIsNotNullAndCloseDateBeforeOrderByPublishDateDesc(Category category, Announcement.DisplayStatus displayStatus, ZonedDateTime closeDate, Pageable pageable);a

}
