package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@Setter

public class AnnouncementsViewDto {
    private int id;
    private String announcementTitle;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
//    private int categoryId;
    private String announcementCategory;

}
