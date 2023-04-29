package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AnnouncementDetailDto {
    private int id;
    private String announcementTitle;
    private String announcementDescription;
//    private int categoryId;
    private String announcementCategory;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;

}
