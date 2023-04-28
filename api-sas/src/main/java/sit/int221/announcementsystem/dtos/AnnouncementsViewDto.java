package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AnnouncementsViewDto {
    private int id;
    private String announcementTitle;
    private String publishDate;
    private String closeDate;
    private String announcementDisplay;
    private int categoryId;
    private String announcementCategory;


}
