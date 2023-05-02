package sit.int221.announcementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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


    @Column(name = "announcementDisplay", nullable = true)
    @Enumerated(EnumType.STRING)
    private DisplayStatus announcementDisplay ;

    public enum DisplayStatus {
        Y,
        N
    }
}

