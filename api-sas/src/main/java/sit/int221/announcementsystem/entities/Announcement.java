package sit.int221.announcementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcementId", nullable = true)
    private Integer id;

    @Column(name = "announcementTitle", nullable = false )
    private String title;

    @Column(name = "announcementDescription", nullable = false)
    private String description;

    @Column(name = "announcementCategory", nullable = false)
    private Integer category;

    @Column(name = "publishDate", nullable = true)
    private String publishDate;

    @Column(name = "closeDate", nullable = true)
    private String closeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "announcementDisplay", nullable = true)
    private DisplayStatus display;

    public enum DisplayStatus {
        Y,
        N
    }
}

