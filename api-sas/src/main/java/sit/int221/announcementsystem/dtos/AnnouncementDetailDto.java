package sit.int221.announcementsystem.dtos;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@Setter
public class AnnouncementDetailDto {
    private String announcementTitle;
    private String announcementDescription;
    @JsonIgnore
    private CategoryDto category;
    private String publishDate;
    private String closeDate;
    private String display;
}
