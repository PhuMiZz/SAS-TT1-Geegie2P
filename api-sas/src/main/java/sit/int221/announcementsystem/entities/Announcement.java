package sit.int221.announcementsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @Column(name = "announcementId", nullable = true)
    private Integer id;

    @Column(name = "announcementTitle", nullable = false )
    private String title;

    @Column(name = "announcementDescription", nullable = false)
    private String description;

    @Column(name = "announcementCategory", nullable = false)
    private String category;

    @Column(name = "publishDate", nullable = true)
    private String publishDate;

    @Column(name = "closeDate", nullable = true)
    private String closeDate;

    @Column(name = "announcementDisplay", nullable = true)
    private String display;
}
