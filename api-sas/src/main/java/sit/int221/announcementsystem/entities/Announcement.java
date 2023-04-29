package sit.int221.announcementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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
    private String announcementTitle;

    @Column(name = "announcementDescription", nullable = false)
    private String announcementDescription;

    @ManyToOne
    @JoinColumn(name = "announcementCategory")
    private Category category;

    @Column(name = "publishDate", nullable = true)
    private ZonedDateTime publishDate;

    @Column(name = "closeDate", nullable = true)
    private ZonedDateTime closeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "announcementDisplay", nullable = true)
    private DisplayStatus announcementDisplay;

    public enum DisplayStatus {
        Y,
        N
    }
}

