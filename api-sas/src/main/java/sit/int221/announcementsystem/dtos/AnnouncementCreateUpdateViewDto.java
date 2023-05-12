package sit.int221.announcementsystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AnnouncementCreateUpdateViewDto {
    private int id;
    @NotBlank(message = "must not be blank") @Size(min = 1, max = 200)
    private String announcementTitle;
    @NotBlank() @Size(min = 1, max = 10000)
    private String announcementDescription;
    private String announcementCategory;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    private int categoryId;
}
