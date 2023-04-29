package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter

public class AnnouncementsViewDto {
    private int id;
    private String announcementTitle;
    private Instant publishDate;
    private Instant closeDate;
    private String announcementDisplay;
//    private int categoryId;
    private String announcementCategory;

}
