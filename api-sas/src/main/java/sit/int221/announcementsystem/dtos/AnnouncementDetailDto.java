package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.Instant;

@Getter
@Setter
public class AnnouncementDetailDto {
    private String announcementTitle;
    private String announcementDescription;
//    @JsonIgnore
//    private CategoryDto category;
    private String announcementCategory;
    private Instant publishDate;
    private Instant closeDate;
    private String announcementDisplay;
}
