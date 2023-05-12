package sit.int221.announcementsystem.dtos;
import lombok.Data;


import java.time.ZonedDateTime;
@Data
public class AnnouncementCreateUpdateViewDto {
    private int id;
    private String announcementTitle;
    private String announcementDescription;
    private String announcementCategory;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    private int categoryId;
}
