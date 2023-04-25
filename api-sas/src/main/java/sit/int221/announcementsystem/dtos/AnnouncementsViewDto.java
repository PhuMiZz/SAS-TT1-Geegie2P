package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import sit.int221.announcementsystem.entities.Announcement;

@Getter
@Setter

public class AnnouncementsViewDto {
    private String id;
    private String announcementTitle;
    private String publishDate;
    private String closeDate;
    private String display;

    @JsonIgnore
    private CategoryDto category;
    
}
