package sit.int221.announcementsystem.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import sit.int221.announcementsystem.entities.Announcement;

import java.time.ZonedDateTime;

@Data
public class AnnouncementCreateDto {
    private int id;
    private String announcementTitle;
    private String announcementDescription;
    private int categoryId;

    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;

    private String announcementDisplay;

}
