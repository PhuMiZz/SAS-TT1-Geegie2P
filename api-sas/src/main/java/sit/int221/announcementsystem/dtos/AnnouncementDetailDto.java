package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.Instant;

@Getter
@Setter
public class AnnouncementDetailDto {
    private int id;
    private String announcementTitle;
    private String announcementDescription;
//    private int categoryId;
    private String announcementCategory;
    private Instant publishDate;
    private Instant closeDate;
    private String announcementDisplay;

}
