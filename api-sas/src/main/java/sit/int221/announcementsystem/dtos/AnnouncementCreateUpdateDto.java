package sit.int221.announcementsystem.dtos;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AnnouncementCreateUpdateDto {
    private int id;
    private String announcementTitle;
    private String announcementDescription;
    private int categoryId;

    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;

    private String announcementDisplay;

}
