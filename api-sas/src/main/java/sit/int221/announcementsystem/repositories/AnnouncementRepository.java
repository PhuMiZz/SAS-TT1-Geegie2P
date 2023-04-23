package sit.int221.announcementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.announcementsystem.entities.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

}
