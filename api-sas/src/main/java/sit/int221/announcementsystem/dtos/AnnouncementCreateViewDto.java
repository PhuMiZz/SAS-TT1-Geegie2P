package sit.int221.announcementsystem.dtos;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AnnouncementCreateViewDto {
    private int id;
    private String announcementTitle;
    private String announcementCategory;
     private String announcementDescription;

    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    private int categoryId;

}
